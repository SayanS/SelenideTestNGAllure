package com.test;

import com.test.util.TestBase;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Features("Shops header menu")
@Stories("Checking features related to Shops item of the Header menu")
public class ShopsTest extends TestBase {

    @Test
    public void isAllCitiesDisplayedOnShopsPage() {
        onHomePage().headerPage.selectByVisibleText("Магазины");
    }

}
