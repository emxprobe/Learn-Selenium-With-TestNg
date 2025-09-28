package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ItemDetailPage;
import pages.LoginPage;
import pages.ShoppingPage;
import utilities.BaseTest;
import utilities.Logs;

public class ItemDetailTests extends BaseTest {

    private final LoginPage loginPage = new LoginPage();
    private final ShoppingPage shoppingPage = new ShoppingPage();
    private final ItemDetailPage itemDetailPage = new ItemDetailPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        Logs.info("Navegando a la url");
        driver.get("https://www.saucedemo.com/");

        loginPage.waitPageToLoad();

        loginPage.fillLogin("standard_user", "secret_sauce");
        shoppingPage.waitPageToLoad();

        shoppingPage.goToItemDetail("Sauce Labs Fleece Jacket");
        itemDetailPage.waitPageToLoad();
    }

    @Test
    public void testVerifyItemDetails() {

        itemDetailPage.verifyPage();
    }
}
