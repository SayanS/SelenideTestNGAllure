package com.test.pages.homepage;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class ProductSection {

    String PRODUCTS_SECTION;

    String PRODUCT_CONTAINER = ".goods-item-content";
    String PRODUCT_CONTAINER_BUY_BUTTON = ".buy-button.sp.valign-wrapper";
    String PRODUCT_CONTAINER_LOAD_MORE = ".load-more";
    String PRODUCT_CONTAINER_TITLE = ".good-description div.title div";
    String SLICK_SLIDE_CATEGORY_MENU = ".slick-slide.menu-wide-item";
    String SLICK_SLIDE_CATEGORY_MENU_VISIBLE_ITEMS = ".slick-active";
    String SLICK_SLIDE_CATEGORY_MENU_ALL_ITEMS = ".slick-slide.menu-wide-item";
    String SLICK_SLIDE_CATEGORY_MENU_NEXT_BUTTON = ".nextPage";
    String SLICK_SLIDE_CATEGORY_MENU_PREV_BUTTON = ".prevPage";

    public ProductSection(String xpath) {
        PRODUCTS_SECTION = xpath;
    }

    @Step
    public ProductSection selectCategoryOfSlickSlackMenuSection(Integer categoryNumber) {
        Integer numberOfVisibleElements;
        Integer numberOfCategories;
        Integer numberOfTubs;

        numberOfVisibleElements = $(By.xpath(PRODUCTS_SECTION)).$$(SLICK_SLIDE_CATEGORY_MENU_VISIBLE_ITEMS).size();
        numberOfCategories = $(By.xpath(PRODUCTS_SECTION)).$$(SLICK_SLIDE_CATEGORY_MENU_ALL_ITEMS).size();

        if (categoryNumber > numberOfCategories) {
            Assert.assertTrue(false, "Max number of the category is " + numberOfCategories + " but requested category number " + categoryNumber);
            return this;
        } else {
            numberOfTubs = (numberOfCategories % numberOfVisibleElements > 0) ? numberOfCategories / numberOfVisibleElements + 1 : numberOfCategories / categoryNumber;
        }
        for (int i = 1; i <= numberOfTubs + 1; i++) {
            for (SelenideElement category : $(By.xpath(PRODUCTS_SECTION)).$$(SLICK_SLIDE_CATEGORY_MENU_VISIBLE_ITEMS)) {
                if (category.getAttribute("data-index").equals(String.valueOf(categoryNumber - 1)) == true) {
                    category.click();
                    return this;
                }
            }
            $(By.xpath(PRODUCTS_SECTION)).$(SLICK_SLIDE_CATEGORY_MENU_NEXT_BUTTON).click();
        }
        return this;
    }

    @Step
    public SelenideElement loadMoreProductsUntilVisabilityOfConteiner(Integer productNumber) {
        SelenideElement section = $(By.xpath(PRODUCTS_SECTION));
        while (productNumber > Integer.valueOf(section.$$(PRODUCT_CONTAINER).size() - 1)) {
            section.$(PRODUCT_CONTAINER_LOAD_MORE).click();
        }
        return section.$$(PRODUCT_CONTAINER).get(productNumber - 1);
    }

    @Step
    public HomePage addToCartProduct(Integer productNumber) {
        loadMoreProductsUntilVisabilityOfConteiner(productNumber).$(PRODUCT_CONTAINER_BUY_BUTTON).click();
        return page(HomePage.class);
    }

    @Step
    public String getProductName(Integer productNumber) {
        return loadMoreProductsUntilVisabilityOfConteiner(productNumber).$(PRODUCT_CONTAINER_TITLE).getText();
    }
}
