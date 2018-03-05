package com.test.pages.checkoutpage;


import com.codeborne.selenide.CollectionCondition;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MapContainer {
    private String LEAVES= ".leaflet-marker-icon";
    private By CITY_FIELD= By.xpath(".//input[@name='eldoradoCities']");
    private By AUTOSUGGESTED_CITIES_LIST=By.xpath(".//input[@name='eldoradoCities']/ancestor::div[@class='react-autosuggest__container']/following-sibling::div//ul/li");
    private By CLEAR_CITY_FIELD_BUTTON=By.xpath("//i[@class='icon-cross']");

    @Step
    public void enterCityName(String city){
        $(CITY_FIELD).clear();
        $(CITY_FIELD).val(city);
    }

    @Step
    public void ensureThatAutosuggestedCitiesStartsWith(String text){
        $$(AUTOSUGGESTED_CITIES_LIST).shouldBe(Co,text);
    }


}
