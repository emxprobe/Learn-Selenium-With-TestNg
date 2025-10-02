package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Footer;
import pages.LoginPage;
import pages.ShoppingPage;
import utilities.BaseTest;
import utilities.Logs;

public class FooterTests extends BaseTest {

    Footer footer = new Footer();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {

        commonFlows.goToShoppingPage();
    }

    @Test
    public void testVerifySocialMediaLinks() {

        footer.verifySocialMediaLink(
                "https://www.linkedin.com/company/sauce-labs/",
                "https://www.facebook.com/saucelabs",
                "https://twitter.com/saucelabs");
    }
}
