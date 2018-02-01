package com.test.pages;

import com.codeborne.selenide.Selenide;

public class BasePage {


    public void clearCookies() {
        Selenide.clearBrowserCookies();
    }
}
