package com.test;

import com.test.models.User;
import com.test.util.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

@Test
@Features("SignIn")
@Stories("Checking ability to sign in")
public class SignInTest extends TestBase  {

    @DataProvider(name = "userProvider")
    public Object[][] getNames() {
        User[][] result = new User[2][1];
        result[0][0] = new User("garmsayan@gmail.com", "Rfhfylfitkm12r");
        result[1][0] = new User("lloydp2017@gmail.com","Rfhfylfitkm12r");
        return result;
    }

    @Test(dataProvider = "userProvider")
    public void signIn(User user) {
        onGooglePage().clickOnSignInButton()
                .enterUserEmail(user.getEmail())
                .clickOnNextButtonAfterEmailEntered()
                .enterUserPassword(user.getPassword())
                .clickOnNextButtonAfterPasswordEntered()
                .ensureAccountButtonIsDisplayed();
    }

}
