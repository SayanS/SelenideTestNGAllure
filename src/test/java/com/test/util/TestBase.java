package com.test.util;

import com.test.pages.GooglePage;

import static com.codeborne.selenide.Selenide.open;



public class TestBase {

    public GooglePage onGooglePage(){
        GooglePage page = open("http://google.com/", GooglePage.class);
        return page;
    }

}