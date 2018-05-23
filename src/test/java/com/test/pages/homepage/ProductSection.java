package com.test.pages.homepage;

import com.codeborne.selenide.SelenideElement;
import com.test.models.Product;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

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
        while (productNumber > section.$$(PRODUCT_CONTAINER).size() - 1) {
            section.$(PRODUCT_CONTAINER_LOAD_MORE).click();
        }
        return section.$$(PRODUCT_CONTAINER).get(productNumber - 1);
    }

    @Step
    public Product addToCartProduct(Integer productNumber) {
        String PRODUCT_ID=".goods-code span";
        String PRODUCT_TITLE=".title.lp div";
        String PRODUCT_CURRENT_PRICE=".current-price span";
        String PRODUCT_OLD_PRICE=".old-price-value";
        Product product=new Product();
        SelenideElement productContainer=loadMoreProductsUntilVisabilityOfConteiner(productNumber);
        product.setId(productContainer.find(PRODUCT_ID).getText());
        product.setModelName(productContainer.find(PRODUCT_TITLE).getAttribute("title"));
        product.setQty(1);
        product.setPrice(productContainer.find(PRODUCT_CURRENT_PRICE).getText());
        product.setOldPrice(productContainer.find(PRODUCT_OLD_PRICE).getText());
        productContainer.$(PRODUCT_CONTAINER_BUY_BUTTON).click();
        return product;
    }

    @Step
    public String getProductName(Integer productNumber) {
        return loadMoreProductsUntilVisabilityOfConteiner(productNumber).$(PRODUCT_CONTAINER_TITLE).getText();
    }
}
