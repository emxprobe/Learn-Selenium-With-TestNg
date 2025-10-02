package automation;

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
}
