package com.test.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

public class CatalogMenuPopUp extends BasePage {

    private By title = By.xpath(".//div[@class='catalog-menu-title']");
    private By items = By.xpath(".//li[contains(@id,'mobile-menu-item-display')]/a");
    private By subItemsHeading = By.xpath(".//div[@class='sub-menu-nav']//div[@class='heading']/a/span");

    @Step
    public void ensureThatTitle(String expectedTitle) {
        $(title).shouldBe(Condition.text(expectedTitle));
    }

    @Step
    public void ensureThatAllMenuItemsIsShown(LinkedHashMap<String, LinkedHashMap<String, List<String>>> catalog) {
        $$(items).shouldHave(CollectionCondition.exactTexts(new LinkedList<>(catalog.keySet())));
    }

    @Step
    public void ensureThatHeadingsOfSubItemsShown(LinkedList<String> expectedSubItems) {
        Assert.assertEquals($$(subItemsHeading).texts().stream().map(String::toLowerCase).collect(Collectors.toList()),
                expectedSubItems.stream().map(String::toLowerCase).collect(Collectors.toList()));
//      The same code Using Selenide
        $$(subItemsHeading).shouldBe(CollectionCondition.texts(expectedSubItems));
    }

    @Step
    public void clickOnMenuItem(String item) {
        $$(items).findBy(Condition.text(item)).click();
    }

    @Step
    public NodeItemPage doubleClickOnMenuItem(String item) {
        $$(items).findBy(Condition.text(item)).hover();
        $$(items).findBy(Condition.text(item)).click();
        $$(items).findBy(Condition.text(item)).click();
        return page(NodeItemPage.class);
    }
    @Step
    public boolean isActiveMenuItem(String item) {
        if($$(items).findBy(Condition.text(item)).getAttribute("class").contains("-display-no ")){
            return false;
        }else {
            return true;
        }
    }
}
