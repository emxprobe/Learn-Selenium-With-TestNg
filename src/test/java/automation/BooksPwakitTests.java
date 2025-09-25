package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

import java.util.logging.Logger;

public class BooksPwakitTests extends BaseTest {

    @Test
    public void testShadowDom() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://books-pwakit.appspot.com/");

        Logs.debug("Obteniendo el shadow root");
        final var shadowRoot = driver.
                findElement(By.cssSelector("book-app[apptitle='BOOKS']")).getShadowRoot();

        Logs.debug("Obteniendo el footer a travez del shadow root");
        final var footer = shadowRoot.findElement(By.cssSelector("p"));

        Logs.info("Verificando que el texto sea correcto");
        Assert.assertEquals(footer.getText(), "Made with <3 by the Polymer team.");
    }

    @Test
    public void testShadowDom2() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://books-pwakit.appspot.com/");

        Logs.debug("Obteniendo el shadow root");
        final var shadowRoot = driver.
                findElement(By.cssSelector("book-app[apptitle='BOOKS']")).getShadowRoot();

        Logs.info("Obteniendo la barra de busqueda");
        final var searchBar = shadowRoot.findElement(By.id("input"));

        Logs.info("Escribiendo Hello World en la barra de busqueda y buscando");
        new Actions(driver)
                .click(searchBar)
                .sendKeys("Hello World")
                .sendKeys(Keys.ENTER)
                .perform();

        sleep(1000);

        Logs.debug("Obteniendo el shadow root interno");
        final var internalShadowRoot = shadowRoot.
                findElement(By.cssSelector("book-explore")).getShadowRoot();

        final var bookList = internalShadowRoot.findElement(By.cssSelector("ul"));

        Assert.assertTrue(bookList.isDisplayed());
    }
}
