package com.test.pages;

import com.codeborne.selenide.SelenideElement;
import com.test.models.Product;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class CheckoutPage {
    private String CART_PRODUCT_CONTAINERS = "div.cart div.good-card";
    private String CART_REMOVE_PRODUCT_BUTTON = ".icon.icon-cross";

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
    public void ensureThatCartContains(List<Product> products) {
        products.forEach(product -> {
            for (int i = 0; i < $$(CART_PRODUCT_CONTAINERS).size(); i++) {
                try {
                    product.setId(null);
                    assertThat(getProductFromCart(i), samePropertyValuesAs(product));
                    break;
                } catch (Exception e) {
                  //  Assert.assertTrue(false,"Cart isn't contains" + product.getModelName());
                }
            }
        });

    }

    @Step
    public void clearCart() {
        Integer countProducts = $$(CART_PRODUCT_CONTAINERS).size();
        for (int i = 1; i <= countProducts; i++) {
            $$(CART_REMOVE_PRODUCT_BUTTON).get(0).click();
        }
    }
}
