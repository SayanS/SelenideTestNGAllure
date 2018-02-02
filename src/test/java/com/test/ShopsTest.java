package com.test;

import com.test.pages.ShopsPage;
import com.test.util.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Features("Shops header menu")
@Stories("Checking features related to Shops item of the Header menu")
public class ShopsTest extends TestBase {

    @DataProvider
    public Object[][] shopCities() {
        Set<String> cities = new HashSet<String>(Arrays.asList(
                "Александрия", "Белая Церковь", "Белгород-Днестровский", "Бердичев", "Бердянск", "Борисполь", "Бровары",
                "Винница", "Днепр", "Дрогобыч", "Житомир", "Запорожье", "Ивано-Франковск", "Измаил", "Ирпень",
                "Каменец-Подольский", "Каменское", "Киев", "Коломыя", "Константиновка", "Краматорск", "Кременчуг",
                "Кривой Рог", "Кропивницкий", "Луцк", "Львов", "Мариуполь", "Мелитополь", "Миргород", "Мукачево", "Нежин",
                "Николаев", "Никополь", "Новая Каховка", "Новоград-Волынский", "Новомосковск", "Одесса", "Павлоград",
                "Подольск", "Покровск", "Полтава", "Прилуки", "Ровно", "Славутич", "Славянск", "Стрый", "Сумы", "Тернополь",
                "Ужгород", "Умань", "Фастов", "Харьков", "Херсон", "Хмельницкий", "Черкассы", "Чернигов", "Wrong City"));
//        "Черновцы"
        return new Object[][]{{cities}};
    }


    @Test(dataProvider = "shopCities")
    public void isAllCitiesDisplayedOnShopsPage(Set<String> shopCities) {
        ShopsPage shopsPage = (ShopsPage) onHomePage().headerPage.selectMenuItem("Магазины");
        shopsPage.isShownAllCities(shopCities);
    }

}
