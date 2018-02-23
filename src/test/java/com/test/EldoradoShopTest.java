package com.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.models.Product;
import com.test.models.Shop;
import com.test.pages.*;
import com.test.util.EndPoint;
import com.test.util.RestAssuredConfiguration;
import com.test.util.TestBase;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

@Features("Shop header menu")
@Stories("Checking features related to Shop item of the Header menu")
public class EldoradoShopTest extends TestBase {

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

    @Test(dataProvider = "shopCities", enabled = false)
    public void isAllCitiesDisplayedOnShopsPage(TreeSet<String> shopCities) {
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerSection.selectMenuItem("Магазины");
        shopsPage.isShownAllCities(shopCities);
    }

    @Test(dataProvider = "cityIndexesBestWay", enabled = false)
    public void checkAbilityToSelectDefaultCityBestWay(Integer index) {
        String selectedCityName;
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerSection.selectMenuItem("Магазины");
        selectedCityName = shopsPage.selectCity(index - 1);
        shopsPage.headerSection.ensureThatCityShownInHeaderMenu(selectedCityName);
        shopsPage.ensureThatNotificationContains(selectedCityName);

    }

    @Test(enabled = false)
    public void checkAbilityToSelectDefaultCity() {
        String selectedCityName;
        Integer totalCities;
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerSection.selectMenuItem("Магазины");
        totalCities = shopsPage.getCitiesNumber();
        for (int i = 1; i <= totalCities; i++) {
            selectedCityName = shopsPage.selectCity(i - 1);
            shopsPage.headerSection.ensureThatCityShownInHeaderMenu(selectedCityName);
            shopsPage.ensureThatNotificationContains(selectedCityName);
        }
    }

    @Test(dataProvider = "cityIndexes", enabled = false)
    public void checkAbilityToSelectDefaultCityParallelExecution(List<Integer> cityIndexes) {
        Integer totalCities;
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerSection.selectMenuItem("Магазины");
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

    @Test(enabled = false)
    public void isResponseContainsAllCities() {
        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
        Response response =
                new RestAssuredConfiguration().getResponse(requestSpecification, EndPoint.GET_ALL_SHOPS, HttpStatus.SC_OK);

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(dataProvider = "shopCities", enabled = false)
    public void isGetCityResponseContainsAllCitiesViaRestAssured(TreeSet<String> expectedCities) {
        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
        given().spec(requestSpecification).get(EndPoint.GET_ALL_CITIES).
                then().statusCode(200).log().all();
        List<String> actualCities = given().get(EndPoint.GET_ALL_CITIES).body().path("data.title");

        given().get(EndPoint.GET_ALL_CITIES).then().body("data.title", hasItems(expectedCities.toArray()));

        Assert.assertTrue(actualCities.containsAll(expectedCities));
    }

    @Test(dataProvider = "shopCities", enabled = false, groups={"Smoke"})
    public void isGetCityResponseContainsAllCitiesViaTestNg(TreeSet<String> expectedCities) {
        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
        given().spec(requestSpecification).get(EndPoint.GET_ALL_CITIES).
                then().statusCode(200).log().all();
        List<String> actualCities = given().get(EndPoint.GET_ALL_CITIES).body().path("data.title");
        Assert.assertTrue(actualCities.containsAll(expectedCities));
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

    @Test(dataProvider = "catalogMenuItems", enabled = true, groups="Smoke")
    public void isAppropreateSubItemsShownForCatalogMenuItems(LinkedHashMap<String, LinkedHashMap<String, List<String>>> catalog) {
        Integer menuItemIndex = 1;
        CatalogMenuPopUp catalogMenuPopUp = onHomePage().headerSection.openCatalogMenu();
        catalogMenuPopUp.ensureThatTitle("Каталог товаров");
        catalogMenuPopUp.ensureThatAllMenuItemsIsShown(catalog);
        for (Map.Entry<String, LinkedHashMap<String, List<String>>> entry : catalog.entrySet()) {
            String item = entry.getKey();
            LinkedHashMap<String, List<String>> subItems = entry.getValue();
            if (menuItemIndex == 1) {
                menuItemIndex++;
            } else {
                catalogMenuPopUp.clickOnMenuItem(item);
            }
            catalogMenuPopUp.ensureThatHeadingsOfSubItemsShown(new LinkedList<>(subItems.keySet()));
        }
    }

    @DataProvider(parallel = true)
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

    @Test(dataProvider = "catalogMenuItemsForParallel", enabled = false)
    public void isCatalogMenuItemsOpenedRelatedNodeItemPage(String item, LinkedHashMap<String, List<String>> catalog) {
        CatalogMenuPopUp catalogMenuPopUp = onHomePage().headerSection.openCatalogMenu();
        catalogMenuPopUp.ensureThatTitle("Каталог товаров");
        if (catalogMenuPopUp.isActiveMenuItem(item) == false) {
            catalogMenuPopUp.clickOnMenuItem(item);
            NodeItemPage nodeItempage = new NodeItemPage();
            nodeItempage.ensureThatPageTitleIs(item).ensureThatItemsTitlesShown(new LinkedList<>(catalog.keySet()));
        } else {
            NodeItemPage nodeItempage = catalogMenuPopUp.doubleClickOnMenuItem(item)
                    .ensureThatPageTitleIs(item).ensureThatItemsTitlesShown(new LinkedList<>(catalog.keySet()));
        }
    }

    @DataProvider
    public Object[][] productsForAddingToCart() {
        return new Object[][]{{Arrays.asList(new Product("71215295", "Телевизор ELENBERG 32DH4330 + Т2", "4799.-", null, null, 1),
                new Product("71237899", "Телевизор ELENBERG 39DF4530 +Т2", "6888.-", null, null, 1))},
                {Arrays.asList(new Product("71226991", "Телевизор SAMSUNG QE55Q7CAMUXUA QLED", "78999.-", null, null, 1))}};
    }

    @Test(dataProvider = "productsForAddingToCart", enabled=true, groups={"Smoke"})
    public void checkAbilityToAddProductToCart(List<Product> products) throws InterruptedException {
        HomePage homepage = onHomePage().clearCart();
        products.forEach(product ->
                homepage.headerSection.searchForProductByID(product.getId())
                        .clickOnBuyProductButton()
                        .ensureThatNotificationContains(product.getModelName())
        );
        homepage.headerSection.ensureThatCartItemNumberEqualTo(String.valueOf(products.size()));
        CheckoutPage checkoutPage = homepage.headerSection.clickCartIcon();
        checkoutPage.ensureThatCartContains(products);
    }



}

