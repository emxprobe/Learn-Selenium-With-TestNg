package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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

        Logs.info("Escribimos usuario ");
        driver.findElement(By.id("user-name")).sendKeys(username);

        Logs.info("Escribimos contraña");
        driver.findElement(By.id("password")).sendKeys(password);

        Logs.info("Hacemos click en el boton de login");
        driver.findElement(By.id("login-button")).click();

        Logs.info("Esperamos 2 segundos");
        sleep(2000);
    }

    @Test
    public void testVerifyProduct() {

        fillLogin("standard_user", "secret_sauce");

        final var imageList = driver.findElements(By.cssSelector("img[class='inventory_item_img']"));

        Logs.info("Haciendo click en el primer elemento");
        imageList.get(0).click();

        Logs.info("Esperamos 1 segundo");
        sleep(1000);

        Logs.info("Verificando el detalle del producto");
        softAssert.assertTrue(driver.findElement(By.cssSelector(".inventory_details_name")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.cssSelector(".inventory_details_price")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.cssSelector(".inventory_details_desc")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.id("add-to-cart")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.cssSelector(".inventory_details_img")).isDisplayed());
        softAssert.assertAll();
    }

    @Test
    public void testZToAOrderElements() {

        fillLogin("standard_user", "secret_sauce");

        final var selectElement = driver.findElement(By.className("product_sort_container"));
        final var select = new Select(selectElement);

        Logs.info("Elegimos el orden de Z a A");
        select.selectByValue("za");

        Logs.info("Esperamos 1 segundo");
        sleep(1000);

        Logs.debug("Obtenemos la lista de elementos");
        final var titleList = driver.findElements(By.cssSelector("div[data-test='inventory-item-name']"));

        Logs.debug("Obtenemos el primer elemento de la list");
        final var firstTitle = titleList.getFirst();

        Logs.debug("Obtenemos el ultimo elemento de la lista");
        final var lastTile = titleList.getLast();

        Logs.info("Verificamos los titulos de los productos");
        softAssert.assertEquals(firstTitle.getText(), "Test.allTheThings() T-Shirt (Red)");
        softAssert.assertEquals(lastTile.getText(), "Sauce Labs Backpack");
        softAssert.assertAll();
    }

    @Test
    public void testOrderByLowerPrice() {

        fillLogin("standard_user", "secret_sauce");

        Logs.debug("Obtenemos el select");
        final var selectElement = driver.findElement(By.className("product_sort_container"));

        final var select = new Select(selectElement);

        Logs.info("Seleccionamos ordenar por menor precio");
        select.selectByValue("lohi");

        Logs.debug("Obtenemos la lista de precios");
        final var priceList = driver.findElements(By.className("inventory_item_price"));

        Logs.info("Obtenemos el primer elemento");
        final var firstPrice = priceList.getFirst();

        Logs.info("Obtenemos el ultimo elemento");
        final var lastPrice = priceList.getLast();

        Logs.info("Verificamos los precios");
        softAssert.assertEquals(firstPrice.getText(), "$7.99");
        softAssert.assertEquals(lastPrice.getText(), "$49.99");
        softAssert.assertAll();
    }
}
