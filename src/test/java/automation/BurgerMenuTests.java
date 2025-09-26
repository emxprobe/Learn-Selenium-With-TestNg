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
    private final ShoppingPage shoppingPage = new ShoppingPage();
    private final LoginPage loginPage = new LoginPage();
    private final TopBar topBar = new TopBar();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        Logs.info("Navegando a la url");
        driver.get("https://www.saucedemo.com/");

        loginPage.waitPageToLoad();

        loginPage.fillLogin("standard_user", "secret_sauce");
        shoppingPage.waitPageToLoad();

        topBar.openBurgerMenu();
        burgerMenu.waitPageToLoad();
    }

    @Test
    public void testLogoutTes() {

        burgerMenu.clickLogout();
        loginPage.waitPageToLoad();
        loginPage.verifyPage();
    }
}
