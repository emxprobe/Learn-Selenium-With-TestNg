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
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void masterSetup() {

        Logs.debug("Inicializando el driver");
        driver = new EdgeDriver();

        Logs.debug("Maximizando la pantalla");
        driver.manage().window().maximize();

        Logs.debug("Borrando las cookies");
        driver.manage().deleteAllCookies();



        Logs.debug("Asignando driver al webdriver provider");
        new WebdriverProvider().set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void masterTeardown() {

        Logs.debug("Matando el driver");
        driver.quit();
    }
}
