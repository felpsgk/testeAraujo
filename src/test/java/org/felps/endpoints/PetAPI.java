package org.felps.endpoints;

import io.restassured.response.Response;
import org.felps.model.Pet;

import static io.restassured.RestAssured.given;

public class PetAPI {
    public static Boolean getPet(Pet p,String apikey) {
        if (p.getId() == 0) {
            System.out.println("Erro: Atributos obrigatórios não preenchidos.");
            return false;
        }
        Response r = given()
                .header("api_key", apikey)
                .pathParam("id", p.getId())
                .get("https://petstore.swagger.io/v2/pet/{id}")
                .then()
                .statusCode(404)
                .extract().response();
        String responseBody = r.getBody().asString();
        System.out.println("Resposta da API:");
        System.out.println(responseBody);
        return r.getStatusCode() == 404;
    }
    public static Boolean putPet(Pet p, String apikey) {
        if (p.getId() == 0 ||
            p.getName() == null||
            p.getStatus() == null) {
            System.out.println("Erro: Atributos obrigatórios não preenchidos.");
            return false;
        }
        Response r = given()
                .contentType("application/json")
                .body(p)
                .header("api_key", apikey)
                .put("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200)
                .extract().response();
        String responseBody = r.getBody().asString();
        System.out.println("Resposta da API:");
        System.out.println(responseBody);
        return r.getStatusCode() == 200;
    }
    public static Boolean getPetPending(String apikey) {
        Response r = given()
                .header("api_key", apikey)
                .queryParam("status", "pending")
                .get("https://petstore.swagger.io/v2/pet/findByStatus")
                .then()
                .statusCode(200)
                .extract().response();
        String responseBody = r.getBody().asString();
        System.out.println("Resposta da API:");
        System.out.println(responseBody);
        return r.getStatusCode() == 200;
    }
}
