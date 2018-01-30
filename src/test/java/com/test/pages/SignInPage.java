package com.test.pages;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SignInPage {
    protected final By profileIdentifierButton = By.xpath(".//div[@id='profileIdentifier']");
    protected final By headingTextTitle = By.xpath(".//*[@id='headingText']");
    @Step
    public SignInPage enterUserEmail(String emailValue) {
        $(By.xpath(".//input[@id='identifierId']")).val(emailValue);
        return this;
    }
    @Step
    public  SignInPage clickOnNextButtonAfterEmailEntered(){
        $(By.xpath(".//div[contains(@id,'identifierNext')]")).click();
        return this;
    }
    @Step
    public SignInPage enterUserPassword(String passwordValue){
        $(By.xpath(".//input[@name='password']")).val(passwordValue);
        return this;
    }
    @Step
    public  GooglePage clickOnNextButtonAfterPasswordEntered(){
        $(By.xpath(".//div[contains(@id,'passwordNext')]")).click();
        return page(GooglePage.class);
    }

}
