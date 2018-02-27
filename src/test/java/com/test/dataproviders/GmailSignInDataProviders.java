package com.test.dataproviders;

import com.test.models.User;
import org.testng.annotations.DataProvider;

/**
 * Created by user on 27.02.18.
 */
public class GmailSignInDataProviders {
    @DataProvider(name = "userProvider", parallel = true)
    public Object[][] getNames() {
        User[][] result = new User[4][1];
        result[0][0] = new User("garmsayan@gmail.com", "Rfhfylfitkm12r");
        result[1][0] = new User("lloydp2017@gmail.com", "Rfhfylfitkm12r");
        result[2][0] = new User("lloydp2018@gmail.com", "Rfhfylfitkm12r");
        result[3][0] = new User("lloydp2019@gmail.com", "Rfhfylfitkm12r");
        return result;
    }
}
