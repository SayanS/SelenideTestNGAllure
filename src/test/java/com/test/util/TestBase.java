package com.test.util;

import com.codeborne.selenide.WebDriverRunner;
import com.test.pages.GooglePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;


@Listeners({CustomTestListener.class})
public class TestBase {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        // System.setProperty("webdriver.chrome.driver", "./src/test/resources/webdriver/geckodriver");
        //System.setProperty("selenide.browser", "chrome");
        //System.setProperty("selenide.browser.incognito","true");
//        FirefoxProfile profile = new FirefoxProfile();

//        Configuration.dismissModalDialogs=true;
//        Configuration.browser = "marionette";

        DesiredCapabilities dc = DesiredCapabilities.firefox();
        baseUrl = "https://www.google.com/";
        System.setProperty("webdriver.firefox.driver", "./src/test/resources/webdriver/geckodriver");

        FirefoxOptions opts = new FirefoxOptions();
        opts.addArguments("-private");
        opts.addArguments("--start-maximized");
        opts.setCapability("marionette", true);

        driver = new FirefoxDriver(opts);
        //driver = new FirefoxDriver(dc);
        WebDriverRunner.setWebDriver(driver);
        //   Configuration.dismissModalDialogs=true;
        //  Configuration.browser = "marionette";

    }

    public GooglePage onGooglePage() {
        GooglePage page = open(baseUrl, GooglePage.class);
        return page;
    }

}