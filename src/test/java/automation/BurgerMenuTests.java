package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BurgerMenu;
import pages.LoginPage;
import pages.ShoppingPage;
import pages.TopBar;
import utilities.BaseTest;
import utilities.Logs;

public class BurgerMenuTests extends BaseTest {

    private final BurgerMenu burgerMenu = new BurgerMenu();
    private final LoginPage loginPage = new LoginPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        commonFlows.openBurgerMenu();
    }

    @Test
    public void testLogoutTest() {

        burgerMenu.clickLogout();
        loginPage.waitPageToLoad();
        loginPage.verifyPage();
    }

    @Test
    public void testVerifyAboutLink() {

        burgerMenu.verifyAbout("https://saucelabs.com/");
    }
}
