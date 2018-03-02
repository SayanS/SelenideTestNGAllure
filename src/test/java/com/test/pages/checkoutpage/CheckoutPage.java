package com.test.pages.checkoutpage;

import com.test.pages.BasePage;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CheckoutPage extends BasePage {

    private String CHECKOUT_STEPS_TITLES_XPATH = "(.//div[@class='title-number']/ancestor::div/div[@class='content']/preceding-sibling::div[@class='title'])";

    public CheckoutCart checkoutCart=new CheckoutCart();

    @Step
    public CheckoutStep1 selectCheckoutStep1() {
        $(By.xpath(CHECKOUT_STEPS_TITLES_XPATH + "[1]")).click();
        return page(CheckoutStep1.class);
    }

    @Step
    public CheckoutStep2 selectCheckoutStep2() {
        $(By.xpath(CHECKOUT_STEPS_TITLES_XPATH + "[2]")).click();
        return page(CheckoutStep2.class);
    }

    @Step
    public CheckoutStep3 selectCheckoutStep3() {
        $(By.xpath(CHECKOUT_STEPS_TITLES_XPATH + "[3]")).click();
        return page(CheckoutStep3.class);
    }

    @Step
    public CheckoutStep4 selectCheckoutStep4() {
        $(By.xpath(CHECKOUT_STEPS_TITLES_XPATH + "[4]")).click();
        return page(CheckoutStep4.class);
    }

    @Step
    public CheckoutStep5 selectCheckoutStep5() {
        $(By.xpath(CHECKOUT_STEPS_TITLES_XPATH + "[5]")).click();
        return page(CheckoutStep5.class);
    }

}
