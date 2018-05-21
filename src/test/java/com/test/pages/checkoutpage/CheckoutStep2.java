package com.test.pages.checkoutpage;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CheckoutStep2 extends CheckoutPage {
    String STEP2_FORM = ".//div[contains(@class,'delivery-types')]/div[@class='content']";
    By FROM_SHOP_OPTION = By.xpath(".//input[@value='fromShop']/ancestor::div[contains(@class,'delivery-type-field')]");
    By FROM_NEW_POST_OPTION = By.xpath(".//input[@value='fromNewPost']/ancestor::div[contains(@class,'delivery-type-field')]");
    By FROM_MIST_EXPRESS_OPTION = By.xpath(".//input[@value='fromMeestExpress']/ancestor::div[contains(@class,'delivery-type-field')]");
    By ADDRESS_DELIVERY_OPTION = By.xpath(".//input[@value='addresses']/ancestor::div[contains(@class,'delivery-type-field')]");
    By DELIVERY_ADDRESS_CITY = By.xpath(".//input[@name='citiesInput']");
    By DELIVERY_ADDRESS_STREET = By.xpath(".//input[@name='street']");
    By DELIVERY_ADDRESS_HOUSE_NUMBER = By.xpath(".//input[@name='house']");
    By DELIVERY_ADDRESS_FLAT_NUMBER = By.xpath(".//input[@name='apartment']");
    By DELIVERY_ADDRESS_ADDITIONAL_INFO = By.xpath(".//textarea[@name='comment']");
    By NEXT_STEP_BUTTON = By.xpath(STEP2_FORM+"//div[contains(@class,'continue-button')]");
    String SELECTED_DELIVERY_ADDRESS=".selected-value";

    @Step
    public MapContainer selectGetFromShopOption() {
        $(FROM_SHOP_OPTION).click();
        return page(MapContainer.class);
    }

    @Step
    public MapContainer selectGetFromNewPostOption() {
        $(FROM_NEW_POST_OPTION).click();
        return page(MapContainer.class);
    }

    @Step
    public MapContainer selectGetFromMistExpressOption() {
        $(FROM_MIST_EXPRESS_OPTION).click();
        return page(MapContainer.class);
    }

    @Step
    public CheckoutStep2 selectAddressDeliveryOption() {
        $(ADDRESS_DELIVERY_OPTION).click();
        return page(CheckoutStep2.class);
    }

    @Step
    public CheckoutStep2 enterDeliveryAddress(String city, String street, String houseNumber, String flatNumber, String additionalInfo) {
        $(DELIVERY_ADDRESS_CITY).clear();
        $(DELIVERY_ADDRESS_CITY).sendKeys(city);
        $(DELIVERY_ADDRESS_STREET).clear();
        $(DELIVERY_ADDRESS_STREET).sendKeys(street);
        $(DELIVERY_ADDRESS_HOUSE_NUMBER).clear();
        $(DELIVERY_ADDRESS_HOUSE_NUMBER).sendKeys(houseNumber);
        $(DELIVERY_ADDRESS_FLAT_NUMBER).clear();
        $(DELIVERY_ADDRESS_FLAT_NUMBER).sendKeys(flatNumber);
        $(DELIVERY_ADDRESS_ADDITIONAL_INFO).clear();
        $(DELIVERY_ADDRESS_ADDITIONAL_INFO).sendKeys(additionalInfo);
        return this;
    }

    @Step
    public CheckoutStep3 clickOnNextStepButton(){
        $(NEXT_STEP_BUTTON).click();
        return page(CheckoutStep3.class);
    }

    @Step
    public CheckoutStep2 ensureThatSelectedDeliveryAddessContains(String shopCode, String address){
        $(SELECTED_DELIVERY_ADDRESS).shouldBe(Condition.text(shopCode));
        $(SELECTED_DELIVERY_ADDRESS).shouldBe(Condition.text(address));
        return page(CheckoutStep2.class);
    }

}
