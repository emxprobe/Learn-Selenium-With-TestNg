package utilities;

import listeners.SuiteListeners;
import listeners.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Listeners({TestListeners.class, SuiteListeners.class})
public class BaseTest {

    protected final String regression = "regression";
    protected final String smoke = "smoke";
    protected final CommonFlows commonFlows = new CommonFlows();
    private final DriverManager driverManager = new DriverManager();

    @BeforeMethod(alwaysRun = true)
    public void masterSetup() {

       driverManager.buildDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void masterTeardown() {

        driverManager.killDriver();
    }
}
