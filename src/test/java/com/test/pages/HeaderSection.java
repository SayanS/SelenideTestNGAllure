package com.test.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class HeaderSection {
    private By menuItems= By.xpath(".//div[@class='header-content desktop-header']//ul[@class='header-navigation']/li/a");
    private By selectCity= By.xpath(".//div[@class='header-content desktop-header']//ul[@class='header-navigation']/li[contains(@class,'city-select')]");
    private By openCatalogMenuButton=By.xpath(".//div[@class='header-content desktop-header']//li[@class='menu-button']");

    @Step
    public BasePage selectMenuItem(String itemName){
        $$(menuItems).findBy(Condition.text(itemName)).click();
        switch(itemName){
            case "Магазины": return page(ShopsPage.class);
            default:return page(BasePage.class);
        }
    }

    @Step
    public void ensureThatCityShownInHeaderMenu(String expectedCity){
        $(selectCity).waitUntil(Condition.text(expectedCity),5000);
        $(selectCity).shouldBe(Condition.exactText(expectedCity));
    }

    @Step
    public CatalogMenuPopUp openCatalogMenu(){
        $(openCatalogMenuButton).click();
        return page(CatalogMenuPopUp.class);
    }



}
