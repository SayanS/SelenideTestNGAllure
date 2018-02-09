package com.test.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;

public class CatalogMenuPopUp extends BasePage{

    private By title= By.xpath(".//div[@class='catalog-menu-title']");

    @Step
    public void ensureThatTitle(String expectedTitle){
        $(title).shouldBe(Condition.text(expectedTitle));
    }

}
