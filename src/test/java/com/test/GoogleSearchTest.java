package com.test;

import com.codeborne.selenide.Configuration;
import com.test.util.TestBase;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;


public class GoogleSearchTest extends TestBase {

    @Test
    @Step("Check ability to search by text")
    public void userCanSearch() {
       // System.setProperty("webdriver.chrome.driver", "./src/test/resources/webdriver/geckodriver");
        Configuration.browser="marionette";
        //System.setProperty("selenide.browser", "chrome");

        onGooglePage()
                .searchFor("selenide")
                .ensureResultsContains("selenide")
                .ensureResultsHaveSize(10);

        onGooglePage().searchFor("Selenium")
                .ensureResultsContains("Selenium");
    }
}