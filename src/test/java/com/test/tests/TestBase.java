package com.test.tests;

import com.codeborne.selenide.Configuration;
import com.test.pages.GooglePage;
import com.test.pages.HomePage;
import com.test.util.CustomTestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
@Test(groups = {"new","all"})
@Listeners({CustomTestListener.class})
public class TestBase {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
//        In case webdriver initialize by Selenide
//        System.setProperty("webdriver.firefox.driver", "./src/test/resources/webdrivers/geckodriver");
//        Configuration.dismissModalDialogs = true;
//        Configuration.browser = "marionette";
        Configuration.startMaximized = true;

//        In case webdriver initialize by Selenide
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/webdrivers/chromedriver");
//        System.setProperty("Selenide.browser", "chrome");
        Configuration.browser="chrome";
        Configuration.baseUrl= "https://eldorado.ua/";

//       Custom webdriver 1-st approach (the second one - using CustomWebDriver.class)
//       FirefoxOptions opts = new FirefoxOptions();
//       opts.addArguments("-private");
//       opts.addArguments("--start-maximized");
//       opts.setCapability("marionette", true);
//       driver = new FirefoxDriver(opts);
//       WebDriverRunner.setWebDriver(driver);

//        In case custom webdriver 2-nd approach
//        System.setProperty("browser", "com.test.util.CustomWebDriver");

//        baseUrl = System.getProperty("base.url");
//        if (baseUrl == null) {
////            baseUrl="https://www.google.com/";
//            baseUrl = "https://eldorado.ua/";
//        }
        onHomePage().headerSection.acceptGeolocationCity();
    }

    @Step
    public GooglePage onGooglePage(String url) {
        GooglePage page = open(url, GooglePage.class);
        return page;
    }

    @Step
    public HomePage onHomePage() {
        HomePage page = open(baseUrl, HomePage.class);
        return page;
    }

//    clean test site -Drun.browser=chrome -Dbase.url=https://eldorado.ua/ -DsuiteXml=eldoradoTestng.xml
}