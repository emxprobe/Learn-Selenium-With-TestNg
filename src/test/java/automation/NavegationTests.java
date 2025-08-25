package automation;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class NavegationTests extends BaseTest {

    @Test(groups = {regression, smoke})
    public void testUrlIsTheSame() {

        final var url = "https://www.saucedemo.com/";

        Logs.info("Navegamos a la pagina: %s", url);
        driver.get(url);

        Logs.info("Obtenemos la pagina del driver");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificamos que la pagina sea igual a la url");
        Assert.assertEquals(currentUrl, url);
    }

    @Test(groups = regression)
    public void testToHerokuThenGithubThenHeroku() {

        final String urlHeroku = "https://the-internet.herokuapp.com/";
        final String urlGithub = "https://github.com/";

        Logs.info("Navegamos a la pagina: %s", urlHeroku);
        driver.get(urlHeroku);

        Logs.info("Navegamos a la pagina: %s", urlGithub);
        driver.get(urlGithub);

        Logs.info("Regresamos a la pagina anterior: %s", urlHeroku);
        driver.navigate().back();

        Logs.info("Obtenemos la pagina actual");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificamos que la paginas sean iguales");
        Assert.assertEquals(currentUrl, urlHeroku);
    }

    @Test(groups = {regression, smoke})
    public void testAlwaysFail() {

        final String  urlHeroku = "https://the-internet.herokuapp.com/";

        Logs.info("Navegamos a la pagina: %s", urlHeroku);
        driver.get(urlHeroku);

        Logs.info("Obteniendo la url actual");
        final var currentUrls = driver.getCurrentUrl();

        Logs.debug("Verificamos que la paginas sean iguales");
        Assert.assertEquals(currentUrls, "helloWorld");
    }
}
