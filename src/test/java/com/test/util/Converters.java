package com.test.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Converters {
    ObjectMapper mapper = new ObjectMapper();

    public <T> Map<String, T> jsonPojoToMap(Class<T> pojoClass, String pathJsonFile) {
        T pojo = null;
        Gson gson = new GsonBuilder().create();
        {
            try {
                pojo = mapper.readValue(new File(pathJsonFile), new TypeReference<T>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String json = gson.toJson(pojo);
        return new Gson().fromJson(json, Map.class);
    }

    public <T> Map<String, Object> pojoToMap(T pojo) {
        return mapper.convertValue(pojo, Map.class);
    }

    public <T> T mapToObject(Map<String, String> map, Class<T> pojoClass) {
       return mapper.convertValue(map,pojoClass);
}


}
