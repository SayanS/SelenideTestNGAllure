package com.test.pages;

import com.codeborne.selenide.Condition;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ProductPage extends BasePage {
    private String buyProductButton=".goods-inner-container.goods-inner-container-header .buy-button.sp.valign-wrapper";

    @Step
    public ProductPage clickOnBuyProductButton(){
        $(buyProductButton).waitUntil(Condition.visible,10000).click();
        return page(ProductPage.class);
    }
}
