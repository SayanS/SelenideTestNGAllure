package com.test.dataproviders;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.models.Product;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class EldoradoShopDataProviders {
    @DataProvider
    public Object[][] shopCities() {
        TreeSet<String> cities = new TreeSet<>(Arrays.asList(
                "Александрия", "Белая Церковь", "Белгород-Днестровский", "Бердичев", "Бердянск", "Борисполь", "Бровары",
                "Винница", "Днепр", "Дрогобыч", "Житомир", "Запорожье", "Ивано-Франковск", "Измаил", "Ирпень",
                "Каменец-Подольский", "Каменское", "Киев", "Коломыя", "Константиновка", "Краматорск", "Кременчуг",
                "Кривой Рог", "Кропивницкий", "Луцк", "Львов", "Мариуполь", "Мелитополь", "Миргород", "Мукачево", "Нежин",
                "Николаев", "Никополь", "Новая Каховка", "Новоград-Волынский", "Новомосковск", "Одесса", "Павлоград",
                "Подольск", "Покровск", "Полтава", "Прилуки", "Ровно", "Славутич", "Славянск", "Стрый", "Сумы", "Тернополь",
                "Ужгород", "Умань", "Фастов", "Харьков", "Херсон", "Хмельницкий", "Черкассы", "Чернигов", "Черновцы"));
        return new Object[][]{{cities}};
    }

    @DataProvider(parallel = true)
    public Object[][] cityIndexes() {
        Integer number = 57;
        Integer count = 1;
        Integer threads = 4;
        Object[][] indexes = new Object[threads][1];
        List<Integer> indexesList = new LinkedList<>();
        for (int i = 0; i < threads; i++) {
            for (int j = 1; j <= (number - number % threads) / threads; j++) {
                indexesList.add(count);
                count++;
            }
            if ((number % threads > 0) && (i == threads - 1)) {
                for (int j = 1; j <= number % threads; j++) {
                    indexesList.add(count);
                    count++;
                }
            }
            indexes[i][0] = new ArrayList<>(indexesList);
            indexesList.clear();
        }
        return indexes;
    }

    @DataProvider(parallel = true)
    public Object[][] cityIndexesBestWay() {
        Integer number = 57;
        Object[][] indexes = new Object[number][1];
        for (int i = 1; i <= number; i++) {
            indexes[i - 1][0] = i;
        }
        return indexes;
    }

    @DataProvider
    public Object[][] catalogMenuItems() throws IOException {
        Object[][] result = new Object[1][1];
        Integer i = 0;
        LinkedHashMap<String, LinkedHashMap<String, List<String>>> catalogMenuItem = new LinkedHashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        catalogMenuItem = mapper.readValue(new File("./src/test/resources/data/CatalogMenu.json"),
                new TypeReference<LinkedHashMap<String, LinkedHashMap<String, List<String>>>>() {
                });
        result[0][0] = catalogMenuItem;
        return result;
    }

    @DataProvider()
    public Object[][] catalogMenuItemsForParallel() throws IOException {
        Object[][] result;
        Integer i = 0;
        LinkedHashMap<String, LinkedHashMap<String, List<String>>> catalogMenuItem = new LinkedHashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        catalogMenuItem = mapper.readValue(new File("./src/test/resources/data/CatalogMenu.json"),
                new TypeReference<LinkedHashMap<String, LinkedHashMap<String, List<String>>>>() {
                });
        result = new Object[catalogMenuItem.keySet().size()][2];
        for (Map.Entry<String, LinkedHashMap<String, List<String>>> entry : catalogMenuItem.entrySet()) {
            result[i][0] = entry.getKey();
            result[i][1] = entry.getValue();
            i++;
        }
        return result;
    }

    @DataProvider
    public Object[][] productsForAddingToCart() {
        return new Object[][]{{Arrays.asList(new Product("","71215295", "Телевизор ELENBERG 32DH4330 + Т2", "4799.-", null, null,null, 1),
                new Product("","71237899", "Телевизор ELENBERG 39DF4530 +Т2", "6888.-", null, null, null, 1))},
                {Arrays.asList(new Product("","71226991", "Телевизор SAMSUNG QE55Q7CAMUXUA QLED", "78999.-", null, null, null, 1))}};
    }

}
