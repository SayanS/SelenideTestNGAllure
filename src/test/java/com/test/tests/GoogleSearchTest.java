package com.test.tests;

import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Test
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