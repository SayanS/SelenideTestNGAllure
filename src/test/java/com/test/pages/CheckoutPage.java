package com.test.pages;

import com.codeborne.selenide.SelenideElement;
import com.test.models.Product;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

//import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


public class CheckoutPage extends BasePage {
    private String CART_PRODUCT_CONTAINERS = "div.cart div.good-card";
    private String CART_REMOVE_PRODUCT_BUTTON = ".icon.icon-cross";
    private String CART_PRODUCT_CONTAINER_BY_NAME=".//div[@class='cart']//div[.='$ProductName' and @class='checkout-product-image']/ancestor::div[1]";

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
            SelenideElement productContainer=$(By.xpath(CART_PRODUCT_CONTAINER_BY_NAME.replace("$ProductName",product.getModelName())));
            productContainer.find(".price").shouldHave(exactText(product.getPrice().replace(".-","")));
            productContainer.find(".count-value").shouldHave(text(product.getQty().toString()));
        });
        return page(CheckoutPage.class);
    }

    @Step
    public BasePage clearCart() {
        if ($$(CART_REMOVE_PRODUCT_BUTTON).size() > 1) {
            $$(CART_REMOVE_PRODUCT_BUTTON).get(0).click();
            clearCart();
            return page(CheckoutPage.class);
        } else {
            $$(CART_REMOVE_PRODUCT_BUTTON).get(0).click();
            return page(HomePage.class);
        }
    }
}
