package automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class TutorialsPointTests extends BaseTest {

    @Test(groups = regression)
    public void testTab() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");

        Logs.debug("Obteniendo el id de la pestaña para identificarla");
        final var tabId = driver.getWindowHandle();

        Logs.debug("Tab id: %s", tabId);

        Logs.info("Haciendo click en el boton de New Tab");
        driver.findElement(By.xpath("//button[text()='New Tab']")).click();

        final var windowHandleSet = driver.getWindowHandles();
        Logs.debug("Window handles set: %s", windowHandleSet);

        Logs.info("Nos posicionamos en la nueva pestaña");
        for (var windowHandle : windowHandleSet) {

            if (!windowHandle.equals(tabId)) {

                driver.switchTo().window(windowHandle);
            }
        }

        Logs.info("Verificando el texto");
        Assert.assertTrue(

                driver.findElement(By.xpath("//h1[text()='New Tab']")).isDisplayed()
        );

        Logs.info("Cerramos la pestaña actual");
        driver.close();

        Logs.info("Regresamos el focus a la ventana original");
        driver.switchTo().window(tabId);

        Logs.info("Verificando que se regreso a la ventana original");
        Assert.assertTrue(

                driver.findElement(By.xpath("//h1[text()='Browser Windows']")).isDisplayed()
        );
    }

    @Test(groups = regression)
    public void testNewWindow() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://www.tutorialspoint.com/selenium/practice/browser-windows.php");

        Logs.debug("Obteniendo el id de la pestaña para identificarla");
        final var windowId = driver.getWindowHandle();

        Logs.debug("Tab id: %s", windowId);

        Logs.info("Haciendo click en el boton de New Window");
        driver.findElement(By.xpath("//button[text()='New Window']")).click();

        final var windowHandleSet = driver.getWindowHandles();
        Logs.debug("Window handles set: %s", windowHandleSet);

        Logs.info("Nos posicionamos en la nueva ventana");
        for (var windowHandle : windowHandleSet) {

            if (!windowHandle.equals(windowId)) {

                driver.switchTo().window(windowHandle);
            }
        }

        Logs.info("Verificando el texto");
        Assert.assertTrue(

                driver.findElement(By.xpath("//h1[text()='New Window']")).isDisplayed()
        );

        Logs.info("Cerramos la ventana actual");
        driver.close();

        Logs.info("Regresamos el focus a la ventana original");
        driver.switchTo().window(windowId);

        Logs.info("Verificando que se regreso a la ventana original");
        Assert.assertTrue(

                driver.findElement(By.xpath("//h1[text()='Browser Windows']")).isDisplayed()
        );
    }

    @Test(groups = regression)
    public void testFrames() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://www.tutorialspoint.com/selenium/practice/nestedframes.php");

        Logs.debug("Nos posicionamos en el frame");
        driver.switchTo().frame("frame1");

        Logs.info("Verificando el titulo del iframe");
        Assert.assertTrue(
                driver.findElement(By.xpath("//h1[text()='New Tab']")).isDisplayed()
        );

        Logs.debug("Regresando a la pagina original");
        driver.switchTo().defaultContent();

        Logs.info("Verificando el titulo de la pagina");
        Assert.assertTrue(

                driver.findElement(By.xpath("//h1[text()='Nested Frames']")).isDisplayed()
        );
    }
}
