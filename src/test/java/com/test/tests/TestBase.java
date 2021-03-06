package com.test.tests;

import com.codeborne.selenide.Configuration;
import com.test.pages.GooglePage;
import com.test.pages.homepage.HomePage;
import com.test.util.CustomTestListener;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


@Test(groups = {"new", "all"})
@Listeners({CustomTestListener.class})
public class TestBase {
//    WebDriver driver;
    @BeforeSuite
    public void configure() throws IOException {
        Properties configProp= new Properties();
        configProp.load(new FileInputStream("./src/test/resources/selenideConfig.properties"));

        RestAssured.baseURI = configProp.getProperty("baseUri");
        RestAssured.basePath = "";
    }

    @BeforeClass
    public void setUp() throws IOException {
//        In case webdriver initialize by Selenide
//        For Selenide version lower then 4.10.01
//        Configuration.browser = "marionette";
//        System.setProperty("webdriver.firefox.driver", "./src/test/resources/webdrivers/geckodriver");

////        In case webdriver initialize by Selenide
//        System.setProperty("webdriver.chrome.driver", "./src/test/resources/webdrivers/chromedriver");
////      System.setProperty("Selenide.browser", "chrome");

        Properties configProp = new Properties();
        configProp.load(new FileInputStream("./src/test/resources/selenideConfig.properties"));

        Configuration.browser = configProp.getProperty("browser");
        Configuration.baseUrl = configProp.getProperty("baseUrl");
        Configuration.dismissModalDialogs = true;
        Configuration.startMaximized = true;



////       Custom webdriver 1-st approach (the second one - using CustomWebDriver.class)
//       FirefoxOptions opts = new FirefoxOptions();
//       opts.addArguments("-private");
//       opts.addArguments("--start-maximized");
//       opts.setCapability("marionette", true);
//       driver = new FirefoxDriver(opts);
//       WebDriverRunner.setWebDriver(driver);

////        In case custom webdriver 2-nd approach
//        System.setProperty("browser", "com.test.util.CustomWebDriver");
//
//        baseUrl = System.getProperty("base.url");
//        if (baseUrl == null) {
//            baseUrl = "https://eldorado.ua/";
//        }
//        onHomePage().headerSection.acceptGeolocationCity();
    }

    @AfterClass
    public void tearDown(){
        if(getWebDriver()!=null){
            getWebDriver().close();
            getWebDriver().quit();
        }
//        Selenide.clearBrowserCookies();
//        Selenide.clearBrowserLocalStorage();
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