package com.test.steps;

import com.test.models.Product;
import com.test.pages.homepage.HomePage;

import java.util.List;

public class CommonSteps {
    private HomePage homePage;

    public CommonSteps() {
    this.homePage=new HomePage();
    }

    public void addToCart(List<Product> products) {
        products.forEach(product -> {
            homePage.headerSection
                    .searchForProductByID(product.getId())
                    .clickOnBuyProductButton().ensureThatNotificationContains(product.getModelName());
        });
    }
}
