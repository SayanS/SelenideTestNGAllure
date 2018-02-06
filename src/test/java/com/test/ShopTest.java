package com.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.models.Shop;
import com.test.pages.ShopsPage;
import com.test.util.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Features("Shop header menu")
@Stories("Checking features related to Shop item of the Header menu")
public class ShopTest extends TestBase {

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
            indexes[i-1][0] = i;
        }
        return indexes;
    }

    @Test(dataProvider = "shopCities")
    public void isAllCitiesDisplayedOnShopsPage(TreeSet<String> shopCities) {
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerPage.selectMenuItem("Магазины");
        shopsPage.isShownAllCities(shopCities);
    }

    @Test(dataProvider = "cityIndexesBestWay")
    public void checkAbilityToSelectDefaultCityBestWay(Integer index) {
        String selectedCityName;
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerPage.selectMenuItem("Магазины");
        selectedCityName = shopsPage.selectCity(index);
        shopsPage.headerSection.ensureThatCityShownInHeaderMenu(selectedCityName);
        shopsPage.ensureThatNotificationContains(selectedCityName);

    }

    @Test
    public void checkAbilityToSelectDefaultCity() {
        String selectedCityName;
        Integer totalCities;
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerPage.selectMenuItem("Магазины");
        totalCities = shopsPage.getCitiesNumber();
        for (int i = 1; i <= totalCities; i++) {
            selectedCityName = shopsPage.selectCity(i - 1);
            shopsPage.headerSection.ensureThatCityShownInHeaderMenu(selectedCityName);
            shopsPage.ensureThatNotificationContains(selectedCityName);
        }
    }

    @Test(dataProvider = "cityIndexes")
    public void checkAbilityToSelectDefaultCityParallelExecution(List<Integer> cityIndexes) {
        Integer totalCities;
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerPage.selectMenuItem("Магазины");
        totalCities = shopsPage.getCitiesNumber();
        cityIndexes.forEach(index -> {
            String selectedCityName;
            selectedCityName = shopsPage.selectCity(index - 1);
            shopsPage.headerSection.ensureThatCityShownInHeaderMenu(selectedCityName);
            shopsPage.ensureThatNotificationContains(selectedCityName);
        });
    }

    @Test(enabled = false)
    public void isAllCitiesInJson() throws IOException {
//        FileReader reader = new FileReader("./src/test/resources/data/shops.json");
//        JsonParser jsonParser = new JsonParser();
//        List<String> citiesId = new ArrayList<>();
//        JsonArray jsObj = jsonParser.parse(reader).getAsJsonArray();
//        jsObj.forEach(jsonObject -> citiesId.add(jsonObject.getAsJsonObject().get("city_id").getAsString()));

        ObjectMapper mapper = new ObjectMapper();
        List<Shop> shops = new ArrayList<>();
//       First variant how to parse JSON in case custom Type
        JavaType myType = mapper.getTypeFactory().constructCollectionType(List.class, Shop.class);
        shops = mapper.readValue(new File("./src/test/resources/data/shops.json"), myType);
//       Second variant how to parse JSON in case custom Type
        shops = mapper.readValue(new File("./src/test/resources/data/shops.json"), new TypeReference<List<Shop>>() {
        });
        TreeSet<String> citiesJson = new TreeSet<>();
        shops.forEach(shop -> citiesJson.add(shop.getName().split(", ")[1]));
    }


}

