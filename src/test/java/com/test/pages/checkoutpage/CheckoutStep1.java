package com.test.pages.checkoutpage;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CheckoutStep1 extends CheckoutPage{
    String FORM_XPATH=".//div[contains(@class,'contact-details')]/div[@class='content']";

    By CONTACT_DETAILS_FIELD_NAME = By.xpath(FORM_XPATH+"//input[@name='name']");
    By CONTACT_DETAILS_FIELD_PHONE = By.xpath(FORM_XPATH+"//input[@name='phone']");
    By CONTACT_DETAILS_FIELD_EMAIL = By.xpath(FORM_XPATH+"//input[@name='email']");
    By CONTACT_DETAILS_FIELD_NEXT_STEP_BUTTON = By.xpath(FORM_XPATH+"//div[@class='continue-button']");

    By CONTACT_DETAILS_FIELD_ERROR_MESSAGE_NAME = By.xpath(FORM_XPATH+".//input[@name='name']/following-sibling::span[@class='error-input']");
    By CONTACT_DETAILS_FIELD_ERROR_MESSAGE_PHONE = By.xpath(FORM_XPATH+".//input[@name='phone']/following-sibling::span[@class='error-input']");
    By CONTACT_DETAILS_FIELD_ERROR_MESSAGE_EMAIL = By.xpath(FORM_XPATH+".//input[@name='email']/following-sibling::span[@class='error-input']");

    By CONTACT_DETAILS_FIELD_REQUIRED_NAME = By.xpath(FORM_XPATH+".//input[@name='name']/following-sibling::span[@class='require']/span");
    By CONTACT_DETAILS_FIELD_REQUIRED_PHONE = By.xpath(FORM_XPATH+".//input[@name='phone']/following-sibling::span[@class='require']/span");
    By CONTACT_DETAILS_FIELD_REQUIRED_EMAIL = By.xpath(FORM_XPATH+".//input[@name='email']/following-sibling::span[@class='require']/span");


    @Step
    public CheckoutStep1 fillContactInformation(String phone, String name, String email) throws InterruptedException {
        $(CONTACT_DETAILS_FIELD_PHONE).append(phone);
        $(CONTACT_DETAILS_FIELD_NAME).clear();
        $(CONTACT_DETAILS_FIELD_NAME).val(name);
        $(CONTACT_DETAILS_FIELD_EMAIL).clear();
        $(CONTACT_DETAILS_FIELD_EMAIL).val(email);
        return this;
    }

    @Step
    public CheckoutStep2 clickOnNextStepButton() {
        $(CONTACT_DETAILS_FIELD_NEXT_STEP_BUTTON).click();
        return page(CheckoutStep2.class);
    }

    @Step
    public void ensureThatContactInformation() {

    }
}
