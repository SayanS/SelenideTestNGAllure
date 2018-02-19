package com.test.util;


import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class CustomWebDriver implements WebDriverProvider {
    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        WebDriver webDriver;
        String browser;
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("./src/test/resources/config.properties");
            prop.load(input);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        if(System.getProperty("run.browser")!=null){
            browser=System.getProperty("run.browser");
        }else{
            browser="firefox";
        }

        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.firefox.driver", prop.getProperty("firefox.path"));
                FirefoxOptions opts = new FirefoxOptions();
                opts.addArguments("--private");
                opts.addArguments("--start-maximized");
                opts.setCapability("marionette", true);
                capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, opts);
                return new FirefoxDriver(capabilities);
            case "chrome":
                System.setProperty("webdriver.chrome.driver", prop.getProperty("chrome.path"));
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("-incognito");
                chromeOptions.addArguments("--start-maximized", "true");
                chromeOptions.addArguments("disable-popup-blocking", "true");
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                return new ChromeDriver(capabilities);
            default:
                System.setProperty("webdriver.chrome.driver", prop.getProperty("chrome.path"));
                ChromeOptions chromeOpts = new ChromeOptions();
                chromeOpts.addArguments("--incognito");
                chromeOpts.addArguments("--start-maximized");
                chromeOpts.addArguments("disable-popup-blocking", "true");
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOpts);
                return new ChromeDriver(capabilities);
        }
    }
}
