package com.test;

import com.test.models.User;
import com.test.util.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.util.*;

@Test
@Features("SignIn")
@Stories("Checking ability to sign in")
public class SignInTest extends TestBase {

    @DataProvider(name = "userProvider")
    public Iterator<Object[]> userProvider() {
        List<User> users = new ArrayList<>(Arrays.asList(new User("garmsayan@gmail.com", "Rfhfylfitkm12r"),
                new User("lloydpharm@gmail.com", "Rfhfylfitkm")
        ));
        Collection<Object[]> data = new ArrayList<Object[]>();
        users.forEach(user -> data.add(new Object[]{user}));
        return data.iterator();
    }

//    @DataProvider(name = "userProvider")
//    public Object[][] userProvider() {
//        return new Object[][]{new Object[]{"garmsayan@gmail.com", "Rfhfylfitkm12r"},
//                new Object[]{"lloydpharm@gmail.com", "Rfhfylfitkm"}};
//    }


    @Test(dataProvider = "userProvider")
    public void signIn(User user) {
        onGooglePage().clickOnSignInButton()
                .enterUserEmail(user.getEmail())
                .enterUserPassword(user.getPassword()).ensureAccountButtonisDisplayed();


    }

}
