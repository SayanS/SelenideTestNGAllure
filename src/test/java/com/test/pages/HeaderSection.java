package com.test.pages;

import com.codeborne.selenide.Condition;
import com.test.pages.checkoutpage.CheckoutPage;
import com.test.pages.homepage.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HeaderSection {
    private By MENU_ITEMS = By.xpath(".//div[@class='header-content desktop-header']//ul[@class='header-navigation']/li/a");
    private By SELECT_CITY = By.xpath(".//div[@class='header-content desktop-header']//ul[@class='header-navigation']/li[contains(@class,'city-select')]");
    private By OPEN_CATALOG_MENU_BUTTON = By.xpath(".//div[@class='header-content desktop-header']//li[@class='menu-button']");
    private String GLOBAL_SEARCH_FIELD = ".header-content.desktop-header #search-field input";
    private String ACCEPT_GEOLOCATION_BUTTON = ".desktop-header .button.submit";
    private String CART_ITEM_NUMBER_ICON = ".desktop-header #cart-field .cart-item-number";
    private String CART_ICON = "a[href='/checkout/'].options-list__item";

    @Step
    public HomePage acceptGeolocationCity() {
        $(ACCEPT_GEOLOCATION_BUTTON).click();
        return page(HomePage.class);
    }

    @Step
    public BasePage selectMenuItem(String itemName) {
        $$(MENU_ITEMS).findBy(text(itemName)).click();
        switch (itemName) {
            case "Магазины":
                return page(ShopsPage.class);
            default:
                return page(BasePage.class);
        }
    }

    @Step
    public void ensureThatCityShownInHeaderMenu(String expectedCity) {
        $(SELECT_CITY).waitUntil(text(expectedCity), 5000);
        $(SELECT_CITY).shouldBe(exactText(expectedCity));
    }

    @Step
    public CatalogMenuPopUp openCatalogMenu() {
        $(OPEN_CATALOG_MENU_BUTTON).click();
        return page(CatalogMenuPopUp.class);
    }

    @Step
    public ProductPage searchForProductByID(String productID) {
        if($(GLOBAL_SEARCH_FIELD).getAttribute("value").length()>0) {
            while(!$(GLOBAL_SEARCH_FIELD).getAttribute("value").equals("")) {
                $(GLOBAL_SEARCH_FIELD).val(Keys.BACK_SPACE.toString());
            }
        }
        $(GLOBAL_SEARCH_FIELD).pressEnter();
        $(GLOBAL_SEARCH_FIELD)
                .val(productID)
                .waitUntil(Condition.attribute("value",productID),5000);
        $(GLOBAL_SEARCH_FIELD).click();
        $(".image-place>img.loading").waitUntil(appear,5000);
        $(".image-place>img.loading").waitUntil(disappear,15000);
        $(GLOBAL_SEARCH_FIELD).pressEnter();
        return page(ProductPage.class);
    }

    @Step
    public void ensureThatCartItemNumberEqualTo(String number) throws InterruptedException {
        $(CART_ITEM_NUMBER_ICON).shouldBe(Condition.visible);
        $(CART_ITEM_NUMBER_ICON).shouldBe(exactText(number));
    }

    @Step
    public CheckoutPage clickCartIcon() throws InterruptedException {
        $(CART_ICON).click();
        return page(CheckoutPage.class);
    }

    @Step
    public String getCartItemCount() {
        return $(CART_ITEM_NUMBER_ICON).getText();
    }
}
