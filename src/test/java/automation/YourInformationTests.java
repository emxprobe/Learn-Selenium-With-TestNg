package automation;

import data.CustomDataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.YourInformationPage;
import utilities.BaseTest;

public class YourInformationTests extends BaseTest {

    YourInformationPage yourInformationPage = new YourInformationPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        commonFlows.goToYourInformationPage();
    }

    @Test(
            groups = {regression},
            dataProviderClass = CustomDataProvider.class,
            dataProvider = CustomDataProvider.DP_ERRORMESSAGE)
    public void testVerifyErrorMessage(
            String firstname,
            String lastname,
            String zipcode,
            String errorMessage) {

        yourInformationPage.fillData(firstname, lastname, zipcode);
        yourInformationPage.verifyErrorMessage(errorMessage);
    }
}
