package automation;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class NavegationTests extends BaseTest {

    @Test
    public void testUrlIsTheSame() {

        final var url = "https://www.saucedemo.com/";

        Logs.info("Navegamos a la pagina: %s", url);
        driver.get(url);

        Logs.info("Obtenemos la pagina del driver");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificamos que la pagina sea igual a la url");
        Assert.assertEquals(currentUrl, url);
    }
}
