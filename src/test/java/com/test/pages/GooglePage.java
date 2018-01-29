package com.test.pages;


import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class GooglePage {

    protected final By accountButton=By.xpath(".//a[contains(@title,'Google Account:')]");

    @Step("Enter into search field and click Enter")
    public SearchResultsPage searchFor(String text) {
        $("#lst-ib").val(text).pressEnter();
        return page(SearchResultsPage.class);
    }

    public SignInPage clickOnSignInButton() {
        $(".//a[contains(@href,'ServiceLogin')]").click();
        return page(SignInPage.class);
    }

    public GooglePage ensureAccountButtonisDisplayed(){
        $(accountButton).shouldBe(visible);
        return this;
    }
}