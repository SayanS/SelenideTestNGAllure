package com.test.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SignInPage {
    protected final By emailField = By.xpath(".//input[@id='identifierId']");
    protected final By nextButtonEmailEntered = By.xpath(".//div[contains(@id,'identifierNext')]/content/span");
    protected final By nextButtonPasswordEntered = By.xpath(".//div[contains(@id,'passwordNext')]/content/span");
    protected final By profileIdentifierButton = By.xpath(".//div[@id='profileIdentifier']");
    protected final By headingTextTitle = By.xpath(".//*[@id='headingText']");
    protected final By passwordField = By.xpath(".//input[@name='password']");

    public SignInPage enterUserEmail(String emailValue) {
        $(emailField).val(emailValue);
        $(nextButtonEmailEntered).click();
        return this;
    }

    public GooglePage enterUserPassword(String passwordValue){
        $(passwordField).val(passwordValue);
        $(nextButtonPasswordEntered).click();
        return page(GooglePage.class);
    }


}
