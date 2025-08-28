package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class SauceDemoTests extends BaseTest {

    @Test
    public void testLockedUserMessage() {

        fillLogin("locked_out_user", "secret_sauce");

        final var errorLabel = driver.findElement(By.cssSelector("h3[data-test='error']"));

        Logs.info("Verificamos el mensaje de error");
        softAssert.assertTrue(errorLabel.isDisplayed());
        softAssert.assertEquals(errorLabel.getText(),
                "Epic sadface: Sorry, this user has been locked out.");
        softAssert.assertAll();
    }

    @Test
    public void testValidLogin() {

        fillLogin("standard_user", "secret_sauce");

        final var productsTitle = driver.findElement(By.xpath("//span[text()='Products']"));

        Logs.info("Verificamos que el titulo de productos este visible");
        Assert.assertTrue(productsTitle.isDisplayed());
    }

    private void fillLogin(String username, String password) {

        Logs.info("Navegamos a la pagina de Saucedemo");
        driver.get("https://www.saucedemo.com/");

        Logs.debug("Esperamos 3 segundos");
        sleep(3000);

        Logs.info("Escribimos usuario bloqueado");
        driver.findElement(By.id("user-name")).sendKeys(username);

        Logs.info("Escribimos contraña");
        driver.findElement(By.id("password")).sendKeys(password);

        Logs.info("Hacemos click en el boton de login");
        driver.findElement(By.id("login-button")).click();

        Logs.info("Esperamos 2 segundos");
        sleep(2000);
    }
}
