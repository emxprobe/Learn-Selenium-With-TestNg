package automation;

import com.poiji.annotation.ExcelCellName;
import data.ExcelReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ShoppingPage;
import utilities.BaseTest;
import utilities.Logs;

public class ShoppingTests extends BaseTest {

    private final ShoppingPage shoppingPage = new ShoppingPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        commonFlows.goToShoppingPage();
    }

    @Test
    public void testVerifyShoppingPage() {

        shoppingPage.verifyPage();
    }

    @Test
    public void testVerifyListProduct() {

        final var itemList = ExcelReader.readListItemProductExcel();

        shoppingPage.verifyProductPrice(itemList);

    }
}
