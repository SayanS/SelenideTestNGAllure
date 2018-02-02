package com.test.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class HeaderSection extends BasePage {
    private By menuItems= By.xpath(".//div[@class='header-content desktop-header']//ul[@class='header-navigation']/li/a");

    @Step
    public BasePage selectMenuItem(String itemName){
        $$(menuItems).findBy(Condition.text(itemName)).click();
        switch(itemName){
            case "Магазины": return page(ShopsPage.class);
            default:return page(BasePage.class);
        }
    }

}
