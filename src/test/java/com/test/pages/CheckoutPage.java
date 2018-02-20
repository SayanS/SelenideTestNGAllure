package com.test.pages;

import com.codeborne.selenide.SelenideElement;
import com.test.models.Product;
import io.qameta.allure.Step;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.AllOf.allOf;


public class CheckoutPage extends BasePage {
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
    public CheckoutPage ensureThatCartContains(List<Product> products) {
        assertThat(products, contains(samePropertyValuesAs(getProductFromCart(0))));

        assertThat(products, contains(allOf(hasProperty("id", is(getProductFromCart(0).getId())),
                                            hasProperty("modelName", is(getProductFromCart(0).getModelName())),
                                            hasProperty("discountPrice", is(getProductFromCart(0).getPriceDiscount())),
                                            hasProperty("promotionPrice", is(getProductFromCart(0).getPromotionPrice())),
                                            hasProperty("price", is(getProductFromCart(0).getPrice())),
                                            hasProperty("qty", is(getProductFromCart(0).getQty())))));
int i=0;
//        products.forEach(product -> {
//            Product[] p = new Product[]{getProductFromCart(1)};
//            for (int i = 0; i < $$(CART_PRODUCT_CONTAINERS).size(); i++) {
//                try {
////                    assertThat(getProductFromCart(i), samePropertyValuesAs(product));
//                    product.setId(null);
//                    try {
//                        assertThat(getProductFromCart(i), samePropertyValuesAs(product));
//                        break;
//                    } catch (Exception e) {
//                        System.out.println("Not Equal - next product");
//                    }
//                } catch (Exception e) {
//                }
//            }
//        });
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
