package com.test;

import com.codeborne.selenide.Configuration;
import com.test.util.TestBase;
import org.testng.annotations.Test;


public class GoogleSearchTest extends TestBase {
    @Test
    public void userCanSearch() {
       // System.setProperty("webdriver.chrome.driver", "./src/test/resources/webdriver/geckodriver");
        Configuration.browser="marionette";
        //System.setProperty("selenide.browser", "chrome");

        onGooglePage()
                .searchFor("selenide")
                .ensureResultsContains("selenide252")
                .ensureResultsHaveSize(10);

    }
}