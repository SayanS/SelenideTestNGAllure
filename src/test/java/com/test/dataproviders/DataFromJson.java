package com.test.dataproviders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.models.Product;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataFromJson {
    private ObjectMapper mapper = new ObjectMapper();

    public List<Product> getProducts(){
        try {
            return mapper.readValue(new File("./src/test/resources/data/products.json"), new TypeReference<List<Product>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

//        FileReader reader = new FileReader("./src/test/resources/data/shops.json");
//        JsonParser jsonParser = new JsonParser();
//        List<String> citiesId = new ArrayList<>();
//        JsonArray jsObj = jsonParser.parse(reader).getAsJsonArray();
//        jsObj.forEach(jsonObject -> citiesId.add(jsonObject.getAsJsonObject().get("city_id").getAsString()));

//    ObjectMapper mapper = new ObjectMapper();
//    List<Shop> shops = new ArrayList<>();
//    //       First variant how to parse JSON in case custom Type
//    JavaType myType = mapper.getTypeFactory().constructCollectionType(List.class, Shop.class);
//        shops = mapper.readValue(new File("./src/test/resources/data/shops.json"), myType);
////       Second variant how to parse JSON in case custom Type
//                shops = mapper.readValue(new File("./src/test/resources/data/shops.json"), new TypeReference<List<Shop>>() {
//        });
//        TreeSet<String> citiesJson = new TreeSet<>();
//        shops.forEach(shop -> citiesJson.add(shop.getName().split(", ")[1]));