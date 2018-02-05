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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

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

    @DataProvider
    public Object[][] cityIndexes() {
    return new Object[][]{{1},{5},{56}};
    }


    @Test(dataProvider = "shopCities")
    public void isAllCitiesDisplayedOnShopsPage(TreeSet<String> shopCities) {
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerPage.selectMenuItem("Магазины");
        shopsPage.isShownAllCities(shopCities);
    }

    @Test(dataProvider = "cityIndexes")
    public void isSelectedCityShownInHeaderMenu(Integer cityIndex) {
        String selectedCityName;
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerPage.selectMenuItem("Магазины");
        selectedCityName=shopsPage.selectCity(cityIndex);
        shopsPage.headerSection.ensureThatCityShownInHeaderMenu(selectedCityName);
    }

    @Test
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

