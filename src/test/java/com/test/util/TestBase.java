package com.test.util;

import com.codeborne.selenide.Configuration;
import com.test.pages.GooglePage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Selenide.open;


@Listeners({CustomTestListener.class})
public class TestBase {

    @BeforeTest
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver", "./src/test/resources/webdriver/geckodriver");
        //System.setProperty("selenide.browser", "chrome");
        Configuration.browser = "marionette";
    }

    public GooglePage onGooglePage() {
        GooglePage page = open("http://google.com/", GooglePage.class);
        return page;
    }

}