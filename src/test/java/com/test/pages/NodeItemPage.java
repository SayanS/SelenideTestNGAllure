package com.test.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NodeItemPage extends BasePage {
    private By pageTitle = By.xpath(".//h1");
    private By itemsTitles = By.xpath(".//div[@class='node-item']//div[@class='title']/a");

    @Step
    public NodeItemPage ensureThatPageTitleIs(String expectedTitle) {
        $(pageTitle).shouldBe(text(expectedTitle));
        return this;
    }

    @Step
    public NodeItemPage ensureThatItemsTitlesShown(List<String> expectedTitle) {
        Selenide.Wait().withTimeout(5, TimeUnit.SECONDS).until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        $$(itemsTitles).shouldBe(texts(expectedTitle));
        return this;
    }

}
