package com.test.util;

import com.test.pages.GooglePage;
import com.test.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;


@Listeners({CustomTestListener.class})
public class TestBase {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
//        In case webdriver initialize by Selenide
//       System.setProperty("webdriver.firefox.driver", "./src/test/resources/webdriver/geckodriver");
//       Configuration.dismissModalDialogs = true;
//       Configuration.browser = "marionette";
//       FirefoxOptions opts = new FirefoxOptions();
//       opts.addArguments("-private");
//       opts.addArguments("--start-maximized");
//       opts.setCapability("marionette", true);
//       driver = new FirefoxDriver(opts);
//       WebDriverRunner.setWebDriver(driver);

        System.setProperty("browser", "com.test.util.CustomWebDriver");
        baseUrl = System.getProperty("base.url");
        if (baseUrl == null) {
//            baseUrl="https://www.google.com/";
            baseUrl = "https://eldorado.ua/";
        }
        onHomePage().headerSection.acceptGeolocationCity();
    }

    @Step
    public GooglePage onGooglePage() {
        GooglePage page = open(baseUrl, GooglePage.class);
        return page;
    }

    @Step
    public HomePage onHomePage() {
        HomePage page = open(baseUrl, HomePage.class);
        return page;
    }


}