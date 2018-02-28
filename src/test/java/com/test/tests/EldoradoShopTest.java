package com.test.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dataproviders.EldoradoShopDataProviders;
import com.test.models.Product;
import com.test.models.Shop;
import com.test.pages.*;
import com.test.util.EndPoint;
import com.test.util.RestAssuredConfiguration;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

@Test(dataProviderClass = EldoradoShopDataProviders.class)
@Features("Shop header menu")
@Stories("Checking features related to Shop item of the Header menu")
public class EldoradoShopTest extends TestBase {

    @Test(dataProvider = "shopCities", enabled = false, groups = {"smoke"})
    public void isAllCitiesDisplayedOnShopsPage(TreeSet<String> shopCities) {
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerSection.selectMenuItem("Магазины");
        shopsPage.isShownAllCities(shopCities);
    }

    @Test(dataProvider = "cityIndexesBestWay", enabled = true, groups = {"smoke"})
    public void checkAbilityToSelectDefaultCityBestWay(Integer index) {
        String selectedCityName;
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerSection.selectMenuItem("Магазины");
        selectedCityName = shopsPage.selectCity(index - 1);
        shopsPage.headerSection.ensureThatCityShownInHeaderMenu(selectedCityName);
        shopsPage.ensureThatNotificationContains(selectedCityName);

    }

    @Test(enabled = true, groups = {"smoke"})
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

    @Test(dataProvider = "cityIndexes", enabled = false, groups = {"smoke"})
    public void checkAbilityToSelectDefaultCityParallelExecution(List<Integer> cityIndexes) {
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerSection.selectMenuItem("Магазины");
        cityIndexes.forEach(index -> {
            String selectedCityName;
            selectedCityName = shopsPage.selectCity(index - 1);
            shopsPage.headerSection.ensureThatCityShownInHeaderMenu(selectedCityName);
            shopsPage.ensureThatNotificationContains(selectedCityName);
        });
    }

    @Test(enabled = false, groups = {"smoke"})
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

    @Test(enabled = false, groups = {"smoke"})
    public void isResponseContainsAllCities() {
        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
        Response response =
                new RestAssuredConfiguration().getResponse(requestSpecification, EndPoint.GET_ALL_SHOPS, HttpStatus.SC_OK);

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(dataProvider = "shopCities", enabled = false, groups = {"smoke"})
    public void isGetCityResponseContainsAllCitiesViaRestAssured(TreeSet<String> expectedCities) {
        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
        given().spec(requestSpecification).get(EndPoint.GET_ALL_CITIES).
                then().statusCode(200).log().all();
        List<String> actualCities = given().get(EndPoint.GET_ALL_CITIES).body().path("data.title");

        given().get(EndPoint.GET_ALL_CITIES).then().body("data.title", hasItems(expectedCities.toArray()));

        Assert.assertTrue(actualCities.containsAll(expectedCities));
    }

    @Test(dataProvider = "shopCities", enabled = false, groups = {"smoke"})
    public void isGetCityResponseContainsAllCitiesViaTestNg(TreeSet<String> expectedCities) {
        RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
        given().spec(requestSpecification).get(EndPoint.GET_ALL_CITIES).
                then().statusCode(200).log().all();
        List<String> actualCities = given().get(EndPoint.GET_ALL_CITIES).body().path("data.title");
        Assert.assertTrue(actualCities.containsAll(expectedCities));
    }

    @Test(dataProvider = "catalogMenuItems", enabled = true, groups = "smoke")
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


    @Test(dataProvider = "catalogMenuItemsForParallel", enabled = false, groups = {"smoke"})
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

    @Test(dataProvider = "productsForAddingToCart", enabled = true, groups = {"smoke"})
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

    @Test(enabled=true, groups = {"new"})
    public void checkoutProcess() throws InterruptedException {
        HomePage homePage=onHomePage().clearCart();
        homePage.selectCategoryOfSlickSlackMenuSection(2, 2)
                .addToCartProduct(2,3)
                .ensureThatNotificationContains(homePage.getProductName(2,3));
    }


}

