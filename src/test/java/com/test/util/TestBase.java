package com.test.util;

import com.test.pages.GooglePage;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Selenide.open;


@Listeners({CustomTestListener.class})
public class TestBase {

    public GooglePage onGooglePage(){
        GooglePage page = open("http://google.com/", GooglePage.class);
        return page;
    }

}