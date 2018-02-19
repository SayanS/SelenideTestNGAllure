package com.test.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HeaderSection {
    private By MENU_ITEMS = By.xpath(".//div[@class='header-content desktop-header']//ul[@class='header-navigation']/li/a");
    private By SELECT_CITY = By.xpath(".//div[@class='header-content desktop-header']//ul[@class='header-navigation']/li[contains(@class,'city-select')]");
    private By OPEN_CATALOG_MENU_BUTTON = By.xpath(".//div[@class='header-content desktop-header']//li[@class='menu-button']");
    private String GLOBAL_SEARCH_FIELD = ".header-content.desktop-header #search-field input";
    private String ACCEPT_GEOLOCATION_BUTTON = ".desktop-header .button.submit";
    private String CART_ITEM_NNUMBER_ICON =".desktop-header .cart-item-number.active";
    private String CART_ICON="a[href='/checkout/'].options-list__item";

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
        $(".image-place>img.loading").shouldBe(appear);
        $(".image-place>img.loading").shouldBe(disappear);
        $(GLOBAL_SEARCH_FIELD).pressEnter();
        return page(ProductPage.class);
    }

    @Step
    public void waitForCartItemNumber(Integer delay) throws InterruptedException {
        for(int i=1; i<=delay;i++){
            if($(CART_ITEM_NNUMBER_ICON).getText().equals("0")){
                Thread.sleep(500);
            }else{
                break;
            }
        }
    }

    @Step
    public void ensureThatCartItemNumberEqualTo(String number) throws InterruptedException {
//        $(".notification.notification-visible div").shouldBe(Condition.appear);
//        $(".notification.notification-visible div").shouldBe(Condition.disappear);
        $(CART_ITEM_NNUMBER_ICON).shouldBe(text(number));
    }

    @Step
    public CheckoutPage clickCartIcon() throws InterruptedException {
   //     (new WebDriverWait(getWebDriver(),5)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(CART_ICON)));
        $(".notification.notification-visible div").shouldBe(Condition.appear);
        $(".notification.notification-visible div").shouldBe(Condition.disappear);
        $(CART_ICON).click();
        return page(CheckoutPage.class);
    }
}
