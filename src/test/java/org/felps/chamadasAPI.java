package org.felps;

import org.felps.endpoints.PetAPI;
import org.felps.endpoints.StoreAPI;
import org.felps.model.Category;
import org.felps.model.Pedido;
import org.felps.model.Pet;
import org.felps.model.Tag;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class chamadasAPI {

    final String API_KEY = "special-key";

    @Test
    public void criarNovoPedidoStore(){
        Pedido p = new Pedido();
        p.setId(19283);
        p.setPetId(1223);
        p.setQuantity(3);
        p.setShipDate(chamadasAPI.getDataFormatadaApi());
        p.setStatus("approved");
        p.setComplete(true);
        Assert.assertTrue(StoreAPI.postStore(p,API_KEY));
    }
    @Test
    public void buscaPetInexistente(){
        Pet p = new Pet();
        p.setId(222);
        Assert.assertTrue(PetAPI.getPet(p,API_KEY));
    }
    @Test
    public void atualizarPetExistente(){
        Pet p = new Pet();
        p.setId(5);
        p.setName("Felps");
        p.setStatus("pending");

        Category category = new Category();
        category.setId(1);
        category.setName("dog");
        p.setCategory(category);

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("url foto");
        p.setPhotoUrls(photoUrls);

        List<Tag> tags = new ArrayList<>();
        Tag tag1 = new Tag();
        tag1.setId(0);
        tag1.setName("American Shorthair");
        tags.add(tag1);
        p.setTags(tags);
        Assert.assertTrue(PetAPI.putPet(p,API_KEY));
    }
    @Test
    public void buscaPetStatusPending(){
        Assert.assertTrue(PetAPI.getPetPending(API_KEY));
    }
    public static String getDataFormatadaApi() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return now.format(formatter);
    }
}
