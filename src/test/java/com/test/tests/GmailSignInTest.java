package com.test.tests;

import com.codeborne.selenide.testng.SoftAsserts;
import com.test.dataproviders.GmailSignInDataProviders;
import com.test.models.User;
import com.test.pages.GooglePage;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

@Listeners(SoftAsserts.class)
@Test(dataProviderClass = GmailSignInDataProviders.class)
public class GmailSignInTest extends TestBase {

    @Parameters({ "url" })
    @Test(dataProvider = "userProvider", groups={"all"})
    public void signIn(User user, ITestContext context) throws InterruptedException {
//        onGooglePage(context.getCurrentXmlTest().getParameter("url"))
//                open(context.getCurrentXmlTest().getParameter("url"),GooglePage.class)
        open("https://www.google.com/",GooglePage.class)
                .clickOnSignInButton().enterUserEmail(user.getEmail())
                .clickOnNextButtonAfterEmailEntered()
                .enterUserPassword(user.getPassword())
                .clickOnNextButtonAfterPasswordEntered()
                .ensureAccountButtonIsDisplayed().clearCookies();
    }
//    clean test site -Drun.browser=chrome -Dbase.url=https://eldorado.ua/ -DsuiteXml=eldoradoTestng.xml

}
