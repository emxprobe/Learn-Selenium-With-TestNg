package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ItemDetailPage;
import pages.LoginPage;
import pages.ShoppingPage;
import utilities.BaseTest;
import utilities.Logs;

public class ItemDetailTests extends BaseTest {

    private final ShoppingPage shoppingPage = new ShoppingPage();
    private final ItemDetailPage itemDetailPage = new ItemDetailPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        commonFlows.goToItemDetail("Sauce Labs Fleece Jacket");
    }

    @Test
    public void testVerifyItemDetails() {

        itemDetailPage.verifyPage();
    }

    @Test
    public void testBackToProductsNavigation() {

        itemDetailPage.backToProducts();

        shoppingPage.waitPageToLoad();
        shoppingPage.verifyPage();
    }
}
