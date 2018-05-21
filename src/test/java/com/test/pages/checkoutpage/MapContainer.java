package com.test.pages.checkoutpage;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.test.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class MapContainer extends BasePage {
    private String LEAVES = ".leaflet-marker-icon";
    private By CITY_FIELD = By.xpath(".//input[@name='eldoradoCities']");
    private String SHOP_FIELD = "input[name='shops']";
    private By AUTOSUGGESTED_CITIES_LIST = By.xpath(".//input[@name='eldoradoCities']/ancestor::div[@class='react-autosuggest__container']/following-sibling::div//ul/li");
    private By AUTOSUGGESTED_SHOPS_CONTAINERS = By.xpath(".//input[@name='eldoradoCities']/ancestor::div[@class='react-autosuggest__container']//ul/li//div[@class='row shops-title false']");
    //private By AUTOSUGGESTED_SHOPS_TITLES = By.xpath(AUTOSUGGESTED_SHOPS_CONTAINERS_XPATH + "/div[contains(@class,'city-title')]");
    private By CLEAR_CITY_FIELD_BUTTON = By.xpath("//i[@class='icon-cross']");

    @Step
    public MapContainer enterCityName(String city) {
        $(CITY_FIELD).clear();
        $(CITY_FIELD).val(city);
        return page(MapContainer.class);
    }

    @Step
    public MapContainer ensureThatAutosuggestedCitiesStartsWith(String prefix) {
        $$(AUTOSUGGESTED_CITIES_LIST).forEach(city -> city.shouldHave(Condition.matchText("^" + prefix)));
        return page(MapContainer.class);
    }

    @Step
    public MapContainer selectCity(String cityName) {
        for (SelenideElement city : $$(AUTOSUGGESTED_CITIES_LIST)) {
            if (city.getText().equals(cityName)) {
                city.hover().click();
                return page(MapContainer.class);
            }
        }
        return page(MapContainer.class);
    }

    @Step
    public MapContainer searchShop(String shopCode) {
        $(SHOP_FIELD).sendKeys(shopCode);
            return page(MapContainer.class);
    }

    @Step
    public MapContainer selectFirstShopBy(String addressOrCode){
        $$(AUTOSUGGESTED_SHOPS_CONTAINERS).filter(Condition.text(addressOrCode)).get(0).hover().click();
        return page(MapContainer.class);
    }

    @Step
    public CheckoutStep2 closeMapContainer() {
        (new Actions(getWebDriver())).moveToElement($(".modal-container.modal-map-conainer.modal-show div.modal-content"), 980, 50)
                .click().build().perform();
        return page(CheckoutStep2.class);
    }

}
