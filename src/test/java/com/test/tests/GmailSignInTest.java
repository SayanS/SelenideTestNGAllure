package com.test.tests;

import com.test.dataproviders.GmailSignInDataProviders;
import com.test.models.User;
import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Test(dataProviderClass = GmailSignInDataProviders.class)
@Features("SignIn")
@Stories("Checking ability to sign in")
public class GmailSignInTest extends TestBase {

    @Parameters({ "url" })
    @Test(dataProvider = "userProvider", groups={"new"})
    public void signIn(User user, ITestContext context) throws InterruptedException {
        onGooglePage(context.getCurrentXmlTest().getParameter("url"))
                .clickOnSignInButton().enterUserEmail(user.getEmail())
                .clickOnNextButtonAfterEmailEntered()
                .enterUserPassword(user.getPassword())
                .clickOnNextButtonAfterPasswordEntered()
                .ensureAccountButtonIsDisplayed().clearCookies();
    }
//    clean test site -Drun.browser=chrome -Dbase.url=https://eldorado.ua/ -DsuiteXml=eldoradoTestng.xml

}
