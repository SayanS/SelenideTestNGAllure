package com.test.pages;

import org.openqa.selenium.By;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.Set;

import static com.codeborne.selenide.Selenide.$$;

public class ShopsPage extends BasePage {
    private By cityItems = By.xpath(".//div[contains(@class,'city-item')]/a/span");

    @Step
    public ShopsPage isShownAllCities(Set<String> expectedCities) {
        //Set<String> cities=new HashSet<String>($$(cityItems).texts());
        //cities.removeAll(expectedCities);
        //$$(cityItems).shouldHave(CollectionCondition.exactTexts(new ArrayList<>(expectedCities)));
        $$(cityItems).get(0).isDisplayed();

        Assert.assertEquals($$(cityItems).texts(), expectedCities);
        return this;
    }
}
