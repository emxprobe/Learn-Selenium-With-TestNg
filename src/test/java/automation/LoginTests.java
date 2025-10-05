package automation;

import data.CustomDataProvider;
import data.DataGiver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.BaseTest;
import utilities.Logs;

public class LoginTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        commonFlows.goToLoginPage();
    }

    @Test
    public void testLockedUser() {

        final var lockedCredentials = DataGiver.getLockedCredentials();

        loginPage.fillLogin(
                lockedCredentials.getUsername(),
                lockedCredentials.getPassword());

        loginPage.verifyErrorMessage(
                lockedCredentials.getMessage());
    }

    @Test
    public void testVerifyLoginPage() {

        loginPage.verifyPage();
    }

    @Test
    public void testVerifyUnexistentMessage() {

        final var unexistentCredentials = DataGiver.getUnexistenCredentials();

        loginPage.fillLogin(
                unexistentCredentials.getUsername(),
                unexistentCredentials.getPassword());

        loginPage.verifyErrorMessage(
                unexistentCredentials.getMessage());
    }

    @Test(
            groups = {regression},
            dataProviderClass = CustomDataProvider.class,
            dataProvider = CustomDataProvider.DP_CREDENTIALS)
    public void testInvalidAndUnexistentCredentials(String username, String password, String message) {

        loginPage.fillLogin(username, password);
        loginPage.verifyErrorMessage(message);
    }
}
