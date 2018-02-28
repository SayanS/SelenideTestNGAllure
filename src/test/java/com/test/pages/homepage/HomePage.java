package com.test.pages.homepage;

import com.test.pages.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

public class HomePage extends BasePage {
    String BEST_SUGGESTIONS = "(.//div[@class='row section-items-list']/ancestor::section)[1]";
    String SEASON_SUGGESTIONS="(.//div[@class='row section-items-list']/ancestor::section)[2]";

    public ProductSection bestSuggestions=new ProductSection(BEST_SUGGESTIONS);
    public ProductSection seasonSuggestions=new ProductSection(SEASON_SUGGESTIONS);


    @Step
    public HomePage clearCart() throws InterruptedException {
        if (!headerSection.getCartItemCount().equals("0")) {
            return headerSection.clickCartIcon().clearCart();
        } else {
            return this;
        }
    }



}
