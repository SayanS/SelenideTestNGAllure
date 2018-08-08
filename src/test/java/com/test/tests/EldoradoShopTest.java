package com.test.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dataproviders.DataFromJson;
import com.test.dataproviders.EldoradoShopDataProviders;
import com.test.models.Product;
import com.test.models.Shop;
import com.test.models.User;
import com.test.pages.CatalogMenuPopUp;
import com.test.pages.NodeItemPage;
import com.test.pages.ShopsPage;
import com.test.pages.checkoutpage.CheckoutPage;
import com.test.pages.homepage.HomePage;
import com.test.steps.CommonSteps;
import com.test.util.Converters;
import com.test.util.EndPoint;
import com.test.util.HttpMethods;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

@Test(dataProviderClass = EldoradoShopDataProviders.class)
public class EldoradoShopTest extends TestBase {
    private DataFromJson dataFromJson = new DataFromJson();

    @Test(dataProvider = "shopCities", enabled = false, groups = {"nonexecutable"})
    public void isAllCitiesDisplayedOnShopsPage(TreeSet<String> shopCities) {
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerSection.selectMenuItem("Магазины");
        shopsPage.isShownAllCities(shopCities);
    }

    @Test(dataProvider = "cityIndexesBestWay", enabled = true, groups = {"nonexecutable"})
    public void checkAbilityToSelectDefaultCityBestWay(Integer index) {
        String selectedCityName;
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerSection.selectMenuItem("Магазины");
        selectedCityName = shopsPage.selectCity(index - 1);
        shopsPage.headerSection.ensureThatCityShownInHeaderMenu(selectedCityName);
        shopsPage.ensureThatNotificationContains(selectedCityName);

    }

    @Test(enabled = true, groups = {"nonexecutable"})
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

    @Test(dataProvider = "cityIndexes", enabled = false, groups = {"nonexecutable"})
    public void checkAbilityToSelectDefaultCityParallelExecution(List<Integer> cityIndexes) {
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerSection.selectMenuItem("Магазины");
        cityIndexes.forEach(index -> {
            String selectedCityName;
            selectedCityName = shopsPage.selectCity(index - 1);
            shopsPage.headerSection.ensureThatCityShownInHeaderMenu(selectedCityName);
            shopsPage.ensureThatNotificationContains(selectedCityName);
        });
    }

    @Test(enabled = false, groups = {"nonexecutable"})
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

    @Test(enabled = false, groups = {"nonexecutable"})
    public void isResponseContainsAllCities() {
        Response response =
                new HttpMethods().get(EndPoint.GET_ALL_SHOPS, HttpStatus.SC_OK);

        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(dataProvider = "shopCities", enabled = false, groups = {"nonexecutable"})
    public void isGetCityResponseContainsAllCitiesViaRestAssured(TreeSet<String> expectedCities) {
        (new HttpMethods()).get(EndPoint.GET_ALL_CITIES, HttpStatus.SC_OK).
                then().statusCode(200).log().all();
        List<String> actualCities = given().get(EndPoint.GET_ALL_CITIES).body().path("data.title");

        given().get(EndPoint.GET_ALL_CITIES).then().body("data.title", hasItems(expectedCities.toArray()));

        Assert.assertTrue(actualCities.containsAll(expectedCities));
    }

    @Test(dataProvider = "shopCities", enabled = false, groups = {"nonexecutable"})
    public void isGetCityResponseContainsAllCitiesViaTestNg(TreeSet<String> expectedCities) {
        (new HttpMethods()).get(EndPoint.GET_ALL_CITIES, HttpStatus.SC_OK).
                then().statusCode(200).log().all();
        List<String> actualCities = given().get(EndPoint.GET_ALL_CITIES).body().path("data.title");
        Assert.assertTrue(actualCities.containsAll(expectedCities));
    }

    @Test(dataProvider = "catalogMenuItems", enabled = true, groups = "nonexecutable")
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


    @Test(dataProvider = "catalogMenuItemsForParallel", enabled = false, groups = {"nonexecutable"})
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

    @Test(dataProvider = "productsForAddingToCart", enabled = true, groups = {"nonexecutable"})
    public void checkAbilityToAddProductToCart(List<Product> products) throws InterruptedException {
        HomePage homepage = onHomePage().clearCart();
        products.forEach(product ->
                homepage.headerSection.searchForProductByID(product.getId())
                        .clickOnBuyProductButton()
                        .ensureThatNotificationContains(product.getModelName())
        );
        homepage.headerSection.ensureThatCartItemNumberEqualTo(String.valueOf(products.size()));
        CheckoutPage checkoutPage = homepage.headerSection.clickCartIcon();
        checkoutPage.checkoutCart.ensureThatCartContains(products);
    }

    @Test(enabled = true, groups = {"nonexecutable"})
    public void checkoutProcess() throws InterruptedException {
        Product product = new Product();
        HomePage homePage = onHomePage().clearCart();

        product = homePage.bestSuggestions
                .selectCategoryOfSlickSlackMenuSection(2)
                .addToCartProduct(5);

        homePage.ensureThatNotificationContains(product.getModelName());

        CheckoutPage checkoutPage = homePage.headerSection.clickCartIcon();
        checkoutPage.checkoutCart.ensureThatCartContains(product);

        checkoutPage.selectCheckoutStep1()
                .fillContactInformation("976321452", "Masterok", "masterok@gmail.com")
                .clickOnNextStepButton()
                .selectGetFromShopOption()
                .enterCityName("Ха")
                .ensureThatAutosuggestedCitiesStartsWith("Ха")
                .selectCity("Харьков")
                .selectShopByNumber("46")
                .closeMapContainer()
                .ensureThatSelectedDeliveryAddessContains("Магазин \"Эльдорадо\" (С128), Харьков, Героев Труда, 9 - ТЦ Шок (юр. адрес - Харківська обл., Харківській район, с. Циркуни, вул. Кутузівська, будинок № 19б)")
                .clickOnNextStepButton();
    }

    @Test(enabled = true, groups = {"nonexecutable"})
    public void checkPojoToMap() throws IOException {
        User user;
        ObjectMapper mapper = new ObjectMapper();
        user = mapper.readValue(new File("./src/test/resources/data/user.json"), new TypeReference<User>() {
        });

        Converters converters = new Converters();
        Map<String, User> result = converters.jsonPojoToMap(User.class, "./src/test/resources/data/user.json");

        // json -> Map
//        Gson gson = new GsonBuilder().create();
//        String json = gson.toJson(users.get(0));
//        Map<String,User> result = new Gson().fromJson(json, Map.class);

////        pojo -> Map
//        Map<String, Object> map = mapper.convertValue(users.get(0), Map.class);
        converters.pojoToMap(user);

////         Map -> Pojo
//        mapper.convertValue(map, User.class);
    }

    @Test(enabled = true, groups = {"all"})
    public void checkAbilityToAddProductsToCart() throws InterruptedException {
        List<Product> products = dataFromJson.getProducts();
        Double cartTotal=0.00;
        HomePage homePage = onHomePage().clearCart();
        (new CommonSteps()).addToCart(products);

        CheckoutPage checkoutPage = homePage.headerSection.clickCartIcon();
        checkoutPage.checkoutCart.ensureThatCartContains(products);

        for(int i=0;i<products.size();i++){
            checkoutPage.checkoutCart.clickOnIncQtyFor(products.get(i).getModelName());
            products.get(i).incQty();
            checkoutPage.checkoutCart.ensureThatCartContains(products.get(i));
            cartTotal=cartTotal+products.get(i).getPriceAsDouble();
        }

        checkoutPage.checkoutCart.ensureThatCartContains(products);

        checkoutPage.checkoutCart.ensureThatCartTotalEqualTo(Double.toString(cartTotal));

    }



}

