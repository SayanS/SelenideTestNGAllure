package com.test.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class CatalogMenuPopUp extends BasePage {

    private By TITLE = By.xpath(".//div[@class='catalog-menu-title']");
    private By ITEMS = By.xpath(".//li[contains(@id,'mobile-menu-item-display')]/a");
    private By SUB_ITEMS_HEADING = By.xpath(".//div[@class='sub-menu-nav']//div[@class='heading']/a/span");

    @Step
    public void ensureThatTitle(String expectedTitle) {
        $(TITLE).shouldBe(Condition.text(expectedTitle));
    }

    @Step
    public void ensureThatAllMenuItemsIsShown(LinkedHashMap<String, LinkedHashMap<String, List<String>>> catalog) {
        $$(ITEMS).shouldHave(CollectionCondition.exactTexts(new LinkedList<>(catalog.keySet())));
    }

    @Step
    public void ensureThatHeadingsOfSubItemsShown(LinkedList<String> expectedSubItems) {
        Assert.assertEquals($$(SUB_ITEMS_HEADING).texts().stream().map(String::toLowerCase).collect(Collectors.toList()),
                expectedSubItems.stream().map(String::toLowerCase).collect(Collectors.toList()));
//      The same code Using Selenide
        $$(SUB_ITEMS_HEADING).shouldBe(CollectionCondition.texts(expectedSubItems));
    }

    @Step
    public void clickOnMenuItem(String item) {
        $$(ITEMS).findBy(Condition.text(item)).click();
    }

    @Step
    public NodeItemPage doubleClickOnMenuItem(String item) {
        $$(ITEMS).findBy(Condition.text(item)).hover();
        $$(ITEMS).findBy(Condition.text(item)).click();
        $$(ITEMS).findBy(Condition.text(item)).click();
        return page(NodeItemPage.class);
    }
    @Step
    public boolean isActiveMenuItem(String item) {
        if($$(ITEMS).findBy(Condition.text(item)).getAttribute("class").contains("-display-no ")){
            return false;
        }else {
            return true;
        }
    }
}
