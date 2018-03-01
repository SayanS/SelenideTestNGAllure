package com.test.pages.checkoutpage;

import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;

public class OrderProcessingForm {
    String form;
    String CONTACT_DETAILS_FIELD_NAME = ".contact-details.order-processing-part input[name='name']";
    String CONTACT_DETAILS_FIELD_PHONE = ".contact-details.order-processing-part input[name='phone']";
    String CONTACT_DETAILS_FIELD_EMAIL = ".contact-details.order-processing-part input[name='email']";
    String CONTACT_DETAILS_FIELD_NEXT_STEP_BUTTON = ".contact-details.order-processing-part .continue-button";
    By CONTACT_DETAILS_FIELD_ERROR_MESSAGE_NAME = By.xpath(".//input[@name='name']/following-sibling::span[@class='error-input']");
    By CONTACT_DETAILS_FIELD_ERROR_MESSAGE_PHONE = By.xpath(".//input[@name='phone']/following-sibling::span[@class='error-input']");
    By CONTACT_DETAILS_FIELD_ERROR_MESSAGE_EMAIL = By.xpath(".//input[@name='email']/following-sibling::span[@class='error-input']");

    By CONTACT_DETAILS_FIELD_REQUIRED_NAME = By.xpath(".//input[@name='name']/following-sibling::span[@class='require']/span");
    By CONTACT_DETAILS_FIELD_REQUIRED_PHONE = By.xpath(".//input[@name='phone']/following-sibling::span[@class='require']/span");
    By CONTACT_DETAILS_FIELD_REQUIRED_EMAIL = By.xpath(".//input[@name='email']/following-sibling::span[@class='require']/span");

    String DELIVERY_TYPES = "form .delivery-types.order-processing-part";
    String PROMOTION_BLOCK = "form .promotion-block.order-processing-part";
    String PAYMENT_TYPES = "form .payment-types.order-processing-part";
    String OFFER_DETAILS = "form .offer-details.order-processing-part";

    public OrderProcessingForm(String formSelector){
        this.form=formSelector;
    }

    @Step
    public OrderProcessingForm fillContactInformation(String phone, String name, String email) throws InterruptedException {
        $(CONTACT_DETAILS_FIELD_PHONE).append(phone);
        $(CONTACT_DETAILS_FIELD_NAME).clear();
        $(CONTACT_DETAILS_FIELD_NAME).val(name);
        $(CONTACT_DETAILS_FIELD_EMAIL).clear();
        $(CONTACT_DETAILS_FIELD_EMAIL).val(email);
        $(CONTACT_DETAILS_FIELD_NEXT_STEP_BUTTON).click();
    return this;
    }

    @Step
    public void ensureThatContactInformation(){

    }

}
