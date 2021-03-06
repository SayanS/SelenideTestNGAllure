package com.test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

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



}
