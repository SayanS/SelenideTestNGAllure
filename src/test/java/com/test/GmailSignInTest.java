package com.test;

import com.test.models.User;
import com.test.util.TestBase;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Test
@Features("SignIn")
@Stories("Checking ability to sign in")
public class GmailSignInTest extends TestBase {

    @DataProvider(name = "userProvider", parallel = true)
    public Object[][] getNames() {
        User[][] result = new User[4][1];
        result[0][0] = new User("garmsayan@gmail.com", "Rfhfylfitkm12r");
        result[1][0] = new User("lloydp2017@gmail.com", "Rfhfylfitkm12r");
        result[2][0] = new User("lloydp2018@gmail.com", "Rfhfylfitkm12r");
        result[3][0] = new User("lloydp2019@gmail.com", "Rfhfylfitkm12r");
        return result;
    }
    @Parameters({ "url" })
    @Test(dataProvider = "userProvider", groups={"nonexecutable"})
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
