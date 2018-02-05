package com.test.pages;

import com.codeborne.selenide.Selenide;

public class BasePage {
    public HeaderSection headerSection=new HeaderSection();
    public void clearCookies() {
        Selenide.clearBrowserCookies();
    }
}
