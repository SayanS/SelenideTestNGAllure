package com.test.pages;

public class HomePage extends BasePage {

    public HomePage clearCart() throws InterruptedException {
        if (!headerSection.getCartItemCount().equals("0")) {
            return headerSection.clickCartIcon().clearCart();
        }else {
            return this;
        }
    }
}
