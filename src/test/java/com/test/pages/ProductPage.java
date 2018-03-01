package com.test.pages;

import com.test.pages.checkoutpage.CheckoutPage;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ProductPage extends BasePage {
    private String BUY_PRODUCT_BUTTON=".goods-buy .buy-button.sp.valign-wrapper";
    private String TO_CART_BUTTON=".goods-to-buy-place .to-cart-button.valign-wrapper";

    @Step
    public ProductPage clickOnBuyProductButton(){
        $(BUY_PRODUCT_BUTTON).waitUntil(visible,20000).click();
        return page(ProductPage.class);
    }

    @Step
    public CheckoutPage clickOnToCartButton(){
        $(TO_CART_BUTTON).waitUntil(visible,10000).click();
        return page(CheckoutPage.class);
    }
}
