package utilities;

import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {

    private final boolean runServer = System.getenv("JOB_NAME") != null;

    public void buildDriver(){

        if (runServer) {

            buildRemoteDriver();
        } else {

            buildLocalDriver();
        }
    }

    public void killDriver(){

        Logs.debug("Matando el driver");
        new WebdriverProvider().get().quit();
    }

    private void buildLocalDriver(){

        var browserProperty = System.getProperty("browser");

        if (browserProperty == null){

            Logs.debug("Asignamos default driver a CHROME");
            browserProperty = "CHROME";
        }

        final var browser = Browser.valueOf(browserProperty.toUpperCase());

        Logs.debug("Inicializando el driver: %s", browser);
        final var driver = switch (browser) {

            case CHROME -> new ChromeDriver();
            case EDGE -> new EdgeDriver();
            case FIREFOX -> new FirefoxDriver();
            case SAFARI -> new SafariDriver();
        };


        Logs.debug("Maximizando la pantalla");
        driver.manage().window().maximize();

        Logs.debug("Borrando las cookies");
        driver.manage().deleteAllCookies();

        Logs.debug("Asignando driver al webdriver provider");
        new WebdriverProvider().set(driver);
    }

    private void buildRemoteDriver(){


    }

    private enum Browser{

        CHROME,
        FIREFOX,
        EDGE,
        SAFARI
    }


}
