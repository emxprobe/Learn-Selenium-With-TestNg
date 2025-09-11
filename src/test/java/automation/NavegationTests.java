package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    @Test(groups = regression)
    public void testWaitSauceDemo() {

        final var sauceDemoUrl = "https://www.saucedemo.com/";

        Logs.info("Navegamos a la pagina ");
        driver.get(sauceDemoUrl);

        sleep(3000);

        Logs.info("Obtenemos la url actual");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificamos que la url inicial coincida con la actual");
        Assert.assertEquals(currentUrl, sauceDemoUrl);
    }

    @Test(groups = regression)
    public void testNavigation() {

        final var herokuUrl = "https://the-internet.herokuapp.com/";
        final var githubUrl = "https://github.com/";

        Logs.info("Navegamos a la pagina %s", herokuUrl);
        driver.get(herokuUrl);

        Logs.info("Esperamos 2 segundos");
        sleep(2000);

        Logs.info("Navegamos a la pagina %s", githubUrl);
        driver.get(githubUrl);

        Logs.info("Esperamos 3 segundos");
        sleep(3000);

        Logs.info("Navegamos a la pagina anterior");
        driver.navigate().back();

        Logs.info("Esperamos 2 segundos");
        sleep(2000);

        Logs.info("Obtenemos la url actual");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificamos que la url actual sea igual a la de heroku");
        Assert.assertEquals(currentUrl, herokuUrl);
    }
}
