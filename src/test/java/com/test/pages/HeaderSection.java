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
    private By menuItems = By.xpath(".//div[@class='header-content desktop-header']//ul[@class='header-navigation']/li/a");
    private By selectCity = By.xpath(".//div[@class='header-content desktop-header']//ul[@class='header-navigation']/li[contains(@class,'city-select')]");
    private By openCatalogMenuButton = By.xpath(".//div[@class='header-content desktop-header']//li[@class='menu-button']");
    private String globalSearchField = ".header-content.desktop-header #search-field input";
    private String acceptGeolocationButton = ".desktop-header .button.submit";
    private String cartItemNumberIcon=".desktop-header .cart-item-number.active";

    @Step
    public HomePage acceptGeolocationCity() {
        $(acceptGeolocationButton).click();
        return page(HomePage.class);
    }

    @Step
    public BasePage selectMenuItem(String itemName) {
        $$(menuItems).findBy(text(itemName)).click();
        switch (itemName) {
            case "Магазины":
                return page(ShopsPage.class);
            default:
                return page(BasePage.class);
        }
    }

    @Step
    public void ensureThatCityShownInHeaderMenu(String expectedCity) {
        $(selectCity).waitUntil(text(expectedCity), 5000);
        $(selectCity).shouldBe(exactText(expectedCity));
    }

    @Step
    public CatalogMenuPopUp openCatalogMenu() {
        $(openCatalogMenuButton).click();
        return page(CatalogMenuPopUp.class);
    }

    @Step
    public ProductPage searchForProductByID(String productID) {
        $(globalSearchField).clear();
        $(globalSearchField).val(productID);
        (new WebDriverWait(getWebDriver(),10))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".image-place>img")));
        $(globalSearchField).pressEnter();
        return page(ProductPage.class);
    }

    @Step
    public void ensureThatCartItemNumberEqualTo(String number) throws InterruptedException {
        for(int i=1; i<=10;i++){
            if($(cartItemNumberIcon).getText().equals("0")){
                Thread.sleep(500);
            }else{
                break;
            }
        }
        $(cartItemNumberIcon).shouldBe(text(number));
    }

}
