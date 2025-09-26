package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

import java.time.Duration;

public class SauceDemoTests extends BaseTest {

    @Test(groups = {regression})
    public void testLockedUserMessage() {

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        fillLogin("locked_out_user", "secret_sauce");

        Logs.info("Esperando que cargue la pagina principal");


        final var errorLabel = driver.findElement(By.cssSelector("h3[data-test='error']"));

        Logs.info("Verificamos el mensaje de error");
        softAssert.assertTrue(errorLabel.isDisplayed());
        softAssert.assertEquals(errorLabel.getText(),
                "Epic sadface: Sorry, this user has been locked out.");
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void testValidLogin() {

        fillLogin("standard_user", "secret_sauce");

        final var productsTitle = driver.findElement(By.xpath("//span[text()='Products']"));

        Logs.info("Verificamos que el titulo de productos este visible");
        Assert.assertTrue(productsTitle.isDisplayed());
    }

    private void fillLogin(String username, String password) {

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        Logs.info("Navegamos a la pagina de Saucedemo");
        driver.get("https://www.saucedemo.com/");


        Logs.debug("Esperamos que cargue la pagina principal");
        final var usernameInput = wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.id("user-name")));

        Logs.info("Escribimos usuario ");
        usernameInput.sendKeys(username);

        Logs.info("Escribimos contraña");
        driver.findElement(By.id("password")).sendKeys(password);

        Logs.info("Hacemos click en el boton de login");
        driver.findElement(By.id("login-button")).click();

        if (username.equals("standard_user")) {

            Logs.info("Esperamos que cargue la pagina de shopping");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Products']")));
        }
    }

    @Test(groups = {regression})
    public void testVerifyProduct() {

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        fillLogin("standard_user", "secret_sauce");

        final var imageList = driver.findElements(By.cssSelector("img[class='inventory_item_img']"));

        Logs.info("Haciendo click en el primer elemento");
        imageList.get(0).click();

        Logs.info("Esperamos que cargue el detalle del producto");
        final var inventoryName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".inventory_details_name")));

        Logs.info("Verificando el detalle del producto");
        softAssert.assertTrue(inventoryName.isDisplayed());
        softAssert.assertTrue(driver.findElement(By.cssSelector(".inventory_details_price")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.cssSelector(".inventory_details_desc")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.id("add-to-cart")).isDisplayed());
        softAssert.assertTrue(driver.findElement(By.cssSelector(".inventory_details_img")).isDisplayed());
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void testZToAOrderElements() {

        fillLogin("standard_user", "secret_sauce");

        final var selectElement = driver.findElement(By.className("product_sort_container"));
        final var select = new Select(selectElement);

        Logs.info("Elegimos el orden de Z a A");
        select.selectByValue("za");

        //Logs.info("Esperamos 1 segundo");
        //sleep(1000);

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

    @Test(groups = {regression})
    public void testOrderByLowerPrice() {

        fillLogin("standard_user", "secret_sauce");

        Logs.debug("Obtenemos el select");
        final var selectElement = driver.findElement(By.className("product_sort_container"));

        final var select = new Select(selectElement);

        Logs.info("Seleccionamos ordenar por menor precio");
        select.selectByValue("lohi");

        Logs.debug("Obtenemos la lista de precios");
        final var priceList = driver.findElements(By.className("inventory_item_price"));

        Logs.debug("Obtenemos el primer precio");
        final var firstPrice = Double.parseDouble(
                priceList.getFirst().getText().replace("$", ""));

        Logs.debug("Obtenemos el ultimo precio");
        final var lastPrice = Double.parseDouble(
                priceList.getLast().getText().replace("$", ""));

        Logs.info("Verificamos los precios");
        softAssert.assertEquals(firstPrice, 7.99);
        softAssert.assertEquals(lastPrice, 49.99);
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void testFacebookLink() {

        fillLogin("standard_user", "secret_sauce");

        Logs.info("Obtenemos el label de facebook");
        final var facebookLabel = driver.findElement(By.cssSelector("a[data-test='social-facebook']"));

        Logs.info("Verificando que el hipervinculo este correcto");
        softAssert.assertEquals(facebookLabel.getAttribute("href"), "https://www.facebook.com/saucelabs");
        softAssert.assertTrue(facebookLabel.isDisplayed());
        softAssert.assertTrue(facebookLabel.isEnabled());
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void testLinkedinLink() {

        fillLogin("standard_user", "secret_sauce");

        Logs.info("Obtenemos el label de linkedin");
        final var linkedinLabel = driver.findElement(By.cssSelector("a[data-test='social-linkedin']"));

        Logs.info("Verificamos que el hiperviculo esta correcto");
        softAssert.assertEquals(linkedinLabel.getAttribute("href"), "https://www.linkedin.com/company/sauce-labs/");
        softAssert.assertTrue(linkedinLabel.isDisplayed());
        softAssert.assertTrue(linkedinLabel.isEnabled());
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void testVerifyAboutButton() {

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        fillLogin("standard_user", "secret_sauce");

        Logs.info("Abriendo el burguer menu");
        driver.findElement(By.id("react-burger-menu-btn")).click();

        Logs.info("Esperamos que abra el menu");
        final var aboutLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("about_sidebar_link")));

        Logs.info("Verificanod el link de about");
        softAssert.assertTrue(aboutLink.isDisplayed());
        softAssert.assertTrue(aboutLink.isEnabled());
        softAssert.assertEquals(aboutLink.getAttribute("href"), "https://saucelabs.com/");
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void testVerifyLogout() {

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        fillLogin("standard_user", "secret_sauce");

        Logs.info("Abriendo el burguer menu");
        driver.findElement(By.id("react-burger-menu-btn")).click();

        Logs.info("Esperando que abra el menu");
        final var logoutButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("logout_sidebar_link")));

        Logs.info("Haciendo click en logout");
        logoutButton.click();

        Logs.info("Esperando que llegue a la pagina principal");
        final var loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("login-button")));

        Logs.info("Verificamos que estamos en la pagina de login");
        softAssert.assertTrue(loginButton.isDisplayed());
        softAssert.assertTrue(loginButton.isEnabled());
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void testDeleteCookie() {

        fillLogin("standard_user", "secret_sauce");

        Logs.info("Obteniendo el set de cookies");
        var cookieSet = driver.manage().getCookies();

        Logs.info("Verificando que solo hay 1 cookie");
        Assert.assertEquals(cookieSet.size(), 1);

        Logs.debug("Borramos todas las cookies");
        driver.manage().deleteAllCookies();

        Logs.info("Obteniendo el set de las cookies nuevamente");
        cookieSet = driver.manage().getCookies();

        Logs.info("Verificando que su tamaño sea 0");
        Assert.assertEquals(cookieSet.size(), 0);
    }

    @Test(groups = {regression})
    public void testGetCredentialCookie() {

        fillLogin("standard_user", "secret_sauce");

        Logs.info("Obteniendo la info de la cookie de login");
        final var cookieLogin = driver.manage().getCookieNamed("session-username");

        Logs.info("Verificando que su valor sea standard_user");
        Assert.assertEquals(cookieLogin.getValue(), "standard_user");
    }

    @Test(groups = {regression})
    public void testRelativeLocator() {

        fillLogin("standard_user", "secret_sauce");

        final var locator = (By) RelativeLocator
                .with(By.className("inventory_item_price"))
                .below(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']"));

        final var price = Double.parseDouble(

                driver.findElement(locator).getText().replace("$", "")
        );

        Logs.info("Verificamos que el precio sea correcto");
        Assert.assertEquals(price, 15.99);
    }

    @Test(groups = {regression})
    public void testRemoveCartItem() {

        fillLogin("standard_user", "secret_sauce");

        final var fleeceJacketItemName = driver.findElement(By.xpath("//button[text()='Add to cart']"));

        final var addToCartButtonLocator = (By) RelativeLocator

                .with(By.tagName("button"))
                .below(fleeceJacketItemName);

        var addToCarElement = driver.findElement(addToCartButtonLocator);

        Logs.info("Verificando que el texto sea Add to cart");
        Assert.assertEquals(addToCarElement.getText(), "Add to cart");

        Logs.info("Haciendo click en el boton");
        addToCarElement.click();

        Logs.info("Refrescando elemento de Add to cart");
        addToCarElement = driver.findElement(addToCartButtonLocator);

        Logs.info("Verificando que el texto sea Remove");
        Assert.assertEquals(addToCarElement.getText(), "Remove");
    }
}
