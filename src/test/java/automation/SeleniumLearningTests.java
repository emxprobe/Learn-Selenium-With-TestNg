package automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class SeleniumLearningTests extends BaseTest {

    @Test
    public void testFindDivParentByXpath() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://seleniumlocatorlearning.w3spaces.com/index.html");

        Logs.info("Hacemos click en el boton de continue");
        driver.findElement(By.id("continue-btn")).click();

        Logs.info("Obtenemos el elemento padre");
        final var divFather = driver.findElement(By.xpath("//h1[@id='title']//parent::div"));

        Assert.assertTrue(divFather.isEnabled());
    }

    @Test
    public void testFindH1SonByCSS() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://seleniumlocatorlearning.w3spaces.com/index.html");

        Logs.info("Hacemos click en el boton de continue");
        driver.findElement(By.id("continue-btn")).click();

        Logs.info("Obtenemos el elemento hijo");
        //final var h1Son = driver.findElement(By.cssSelector(""))

    }
}
