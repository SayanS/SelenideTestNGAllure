package com.test.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ShopsPage extends BasePage {
    private By cityItems = By.xpath(".//div[contains(@class,'city-item')]/a/span");
    private By cityLinks=By.xpath(".//div[contains(@class,'city-item')]/a/span/ancestor::a");

    @Step
    public ShopsPage isShownAllCities(TreeSet<String> expectedCities) {
        //Set<String> cities=new HashSet<String>($$(cityItems).texts());
        //cities.removeAll(expectedCities);
        //$$(cityItems).shouldHave(CollectionCondition.exactTexts(new ArrayList<>(expectedCities)));
        $$(cityItems).get(0).isDisplayed();
        List<String> shownCities=new ArrayList<>();
        shownCities=$$(cityItems).texts();
        Collections.sort(shownCities);
        Assert.assertEquals(shownCities, expectedCities);
        return this;
    }

    @Step
    public String selectCity(Integer index){
        String selectedCity=$$(cityLinks).get(index).getText();
        $$(cityLinks).get(index).click();
        return selectedCity;
    }

    @Step
    public Integer getCitiesNumber() {
        return $$(cityItems).size();
    }

    @Step
    public void ensureThatNotificationContains(String expectedText){
        String notificationXpath=".//div[@class='notification notification-info notification-visible']//div";
        $(By.xpath(notificationXpath)).waitUntil(Condition.text(expectedText),20000).shouldHave(text(expectedText));
    }

}
