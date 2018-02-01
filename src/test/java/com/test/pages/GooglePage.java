package com.test.pages;


import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class GooglePage extends BasePage{

    private By accountButton=By.xpath(".//a[contains(@title,'Google Account:')]");

    @Step
    public SearchResultsPage searchFor(String text) {
        $("#lst-ib").val(text).pressEnter();
        return page(SearchResultsPage.class);
    }
    @Step
    public SignInPage clickOnSignInButton() {
        $(By.xpath(".//a[contains(@href,'ServiceLogin')]")).click();
        return page(SignInPage.class);
    }
    @Step
    public GooglePage ensureAccountButtonIsDisplayed(){
        $(accountButton).shouldBe(visible);
        return this;
    }
}