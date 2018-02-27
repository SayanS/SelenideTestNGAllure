package com.test.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$$;

public class HomePage extends BasePage {
    String PRODUCTS_SECTIONS = "(.//div[@class='row section-items-list']/ancestor::section)";
    String SLICK_SLIDE_CATEGORY_MENU = ".slick-slide.menu-wide-item";
    String SLICK_SLIDE_CATEGORY_MENU_VISIBLE_ITEMS = ".slick-active";
    String SLICK_SLIDE_CATEGORY_MENU_ALL_ITEMS = ".slick-slide.menu-wide-item";
    String SLICK_SLIDE_CATEGORY_MENU_NEXT_BUTTON = ".nextPage";
    String SLICK_SLIDE_CATEGORY_MENU_PREV_BUTTON = ".prevPage";

    @Step
    public HomePage clearCart() throws InterruptedException {
        if (!headerSection.getCartItemCount().equals("0")) {
            return headerSection.clickCartIcon().clearCart();
        } else {
            return this;
        }
    }

    @Step
    public HomePage selectCategoryOfSlickSlackMenuSection(Integer sectionNumber, Integer categoryNumber) {
        Integer numberOfVisibleElements = $$(By.xpath(PRODUCTS_SECTIONS)).get(sectionNumber).$$(SLICK_SLIDE_CATEGORY_MENU_VISIBLE_ITEMS).size();
        Integer numberOfCategories = $$(By.xpath(PRODUCTS_SECTIONS)).get(sectionNumber).$$(SLICK_SLIDE_CATEGORY_MENU_ALL_ITEMS).size();
        Integer numberOfTubs = (numberOfCategories % numberOfVisibleElements > 0) ? numberOfCategories / numberOfVisibleElements + 1 : numberOfCategories / categoryNumber;

        for (int i = 1; i <= numberOfTubs+1; i++) {
            for (SelenideElement category : $$(By.xpath(PRODUCTS_SECTIONS)).get(sectionNumber).$$(SLICK_SLIDE_CATEGORY_MENU_VISIBLE_ITEMS)) {
                if (category.getAttribute("data-index").equals(String.valueOf(categoryNumber))==true) {
                    category.click();
                    return this;
                }
            }
            $$(By.xpath(PRODUCTS_SECTIONS)).get(sectionNumber).$(SLICK_SLIDE_CATEGORY_MENU_NEXT_BUTTON).click();
        }
        return this;
    }
}
