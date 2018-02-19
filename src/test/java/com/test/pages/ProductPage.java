package com.test.pages;

import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ProductPage extends BasePage {
    private String buyProductButton=".goods-inner-container.goods-inner-container-header .buy-button.sp.valign-wrapper";
    private String toCartButton=".goods-to-buy-place .to-cart-button.valign-wrapper";

    @Step
    public ProductPage clickOnBuyProductButton(){
        $(buyProductButton).should(visible).click();
        return page(ProductPage.class);
    }

    @Step
    public CheckoutPage clickOnToCartButton(){
        $(toCartButton).waitUntil(visible,10000).click();
        return page(CheckoutPage.class);
    }
}
