package org.felps.endpoints;

import io.restassured.response.Response;
import org.felps.model.Pedido;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThan;

public class StoreAPI {

    public static Boolean postStore(Pedido p, String apikey) {
        if (p.getId() == 0 ||
            p.getPetId() == 0 ||
            p.getQuantity() == 0 ||
            p.getShipDate() == null ||
            p.getStatus() == null ||
            p.getStatus().isEmpty()) {
            System.out.println("Erro: Atributos obrigatórios não preenchidos.");
            return false;
        }
        Response r = given()
                .contentType("application/json")
                .body(p)
                .header("api_key", apikey)
                .post("https://petstore.swagger.io/v2/store/order")
                .then().statusCode(200)
                .extract().response();
        String responseBody = r.getBody().asString();
        System.out.println("Resposta da API:");
        System.out.println(responseBody);
        return r.getStatusCode() == 200;
    }

}
