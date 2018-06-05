package com.test.pages.homepage;

import com.test.pages.BasePage;
import io.qameta.allure.Step;

public class HomePage extends BasePage {
    String BEST_SUGGESTIONS = "(.//div[@class='row section-items-list']/ancestor::section)[1]";
    String SEASON_SUGGESTIONS="(.//div[@class='row section-items-list']/ancestor::section)[2]";

    String DELIVERY_TYPES = "form .delivery-types.order-processing-part";
    String PROMOTION_BLOCK = "form .promotion-block.order-processing-part";
    String PAYMENT_TYPES = "form .payment-types.order-processing-part";
    String OFFER_DETAILS = "form .offer-details.order-processing-part";

    public ProductSection bestSuggestions;
    public ProductSection seasonSuggestions;

    public HomePage(){
        this.bestSuggestions=new ProductSection(BEST_SUGGESTIONS);
        this.seasonSuggestions=new ProductSection(SEASON_SUGGESTIONS);
    }

    @Step
    public HomePage clearCart() throws InterruptedException {
        if (!headerSection.getCartItemCount().equals("0")) {
            return headerSection.clickCartIcon().checkoutCart.clearCart();
        } else {
            return this;
        }
    }

}
