package com.test.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class HeaderSection {
    private By menuItems= By.xpath(".//div[@class='header-content desktop-header']//ul[@class='header-navigation']/li/a");

    public void selectByVisibleText(String text){
        $$(menuItems).findBy(Condition.text(text)).click();
    }

}
