package com.test.pages.checkoutpage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.test.models.Product;
import com.test.pages.BasePage;
import com.test.pages.homepage.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class CheckoutCart extends BasePage{
    private String CART_PRODUCT_CONTAINERS = "div.cart div.good-card";
    private String PRODUCT_NAME = ".product-title.sp";
    private String PRICE = ".price";
    private String QTY = ".count-value";
    private String CART_REMOVE_PRODUCT_BUTTON = ".icon.icon-cross";
    private String CART_PRODUCT_CONTAINER_BY_NAME = ".//div[@class='cart']//div[.='$ProductName' and @class='checkout-product-image']/ancestor::div[1]";

    @Step
    public Product getProductFromCart(Integer index) {
        SelenideElement productContainer = $$(CART_PRODUCT_CONTAINERS).get(index);
        Product product = new Product();
        product.setModelName(productContainer.find(".product-title.sp").getText());
        product.setPrice(productContainer.find(".price").getText());
        product.setQty(Integer.valueOf(productContainer.find(".count-value").getText()));
        return product;
    }

    @Step
    public CheckoutPage ensureThatCartContains(List<Product> products) {
        products.forEach(product -> {
            SelenideElement productContainer = $(By.xpath(CART_PRODUCT_CONTAINER_BY_NAME.replace("$ProductName", product.getModelName())));
            productContainer.find(PRICE).shouldHave(exactText(product.getPrice()));
            productContainer.find(QTY).shouldHave(text(product.getQty().toString()));
        });
        return page(CheckoutPage.class);
    }

    @Step
    public HomePage clearCart() {
        String productName;
        $(By.xpath(".//*[@id='content']/div/div[1]/div[1]")).waitUntil(Condition.not(visible), 10000);
        if ($$(CART_REMOVE_PRODUCT_BUTTON).size() > 1) {
            productName = $$(CART_PRODUCT_CONTAINERS).get(0).find(PRODUCT_NAME).getText();
            $$(CART_REMOVE_PRODUCT_BUTTON).get(0).click();
            ensureThatNotificationContains(productName);
            clearCart();
        } else {
            productName = $$(CART_PRODUCT_CONTAINERS).get(0).find(PRODUCT_NAME).getText();
            $$(CART_REMOVE_PRODUCT_BUTTON).get(0).click();
            ensureThatNotificationContains(productName);
            Selenide.Wait().withTimeout(10, TimeUnit.SECONDS).until(ExpectedConditions.urlToBe(baseUrl));
        }
        return page(HomePage.class);
    }

}
