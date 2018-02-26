package com.test;

import com.test.util.TestBase;
//import io.qameta.allure.Description;
//import io.qameta.allure.Features;
//import io.qameta.allure.Stories;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Test
@Features("Checking search")
@Stories("Checking global search functionality")
public class GoogleSearchTest extends TestBase {
    @DataProvider(name = "searchingTextData1", parallel = true)
    public Object[][] searchingTextData1() {
        return new Object[][]{
                {"Selenide", 10},
                {"Selenium", 10},
                {"Webdriver", 10}
        };
    }

    @DataProvider(name = "searchingTextData2")
    public Object[][] searchingTextData2() {
        return new Object[][]{
                {"C#"},
                {"Java"}
        };
    }

    @Parameters({"url"})
    @Test(dataProvider = "searchingTextData1")
    @Description("1_Some detailed test description")
    public void userCanSearch1(String text, Integer count, ITestContext context) {
        onGooglePage(context.getCurrentXmlTest().getParameter("url"))
                .searchFor(text)
                .ensureResultsContains(text)
                .ensureResultsHaveSize(count);
    }

//    @Test(dataProvider = "searchingTextData2")
//    @Description("2_Some detailed test description")
//    public void userCanSearch2(String text) {
//        onGooglePage()
//                .searchFor(text)
//                .ensureResultsContains(text);
//    }


}