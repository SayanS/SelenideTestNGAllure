package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class HeaderSection {
    private By MENU_ITEMS = By.xpath(".//div[@class='header-content desktop-header']//ul[@class='header-navigation']/li/a");
    private By SELECT_CITY = By.xpath(".//div[@class='header-content desktop-header']//ul[@class='header-navigation']/li[contains(@class,'city-select')]");
    private By OPEN_CATALOG_MENU_BUTTON = By.xpath(".//div[@class='header-content desktop-header']//li[@class='menu-button']");
    private String GLOBAL_SEARCH_FIELD = ".header-content.desktop-header #search-field input";
    private String ACCEPT_GEOLOCATION_BUTTON = ".desktop-header .button.submit";
    private String CART_ITEM_NNUMBER_ICON =".desktop-header .cart-item-number.active";
    private String CART_ICON=".desktop-header .icon-cart";

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
        $(GLOBAL_SEARCH_FIELD).clear();
        $(GLOBAL_SEARCH_FIELD).val(productID);
        (new WebDriverWait(getWebDriver(),10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".image-place>img")));
        $(GLOBAL_SEARCH_FIELD).pressEnter();
        return page(ProductPage.class);
    }

    @Step
    public void ensureThatCartItemNumberEqualTo(String number) throws InterruptedException {
        for(int i=1; i<=10;i++){
            if($(CART_ITEM_NNUMBER_ICON).getText().equals("0")){
                Thread.sleep(500);
            }else{
                break;
            }
        }
        $(CART_ITEM_NNUMBER_ICON).shouldBe(text(number));
    }

    @Step
    public CheckoutPage clickCartIcon() {
        $(CART_ICON).click();
        return page(CheckoutPage.class);
    }
}
