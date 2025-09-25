package automation;

import net.datafaker.Faker;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

import java.time.Duration;

public class DemoQATests extends BaseTest {

    @Test(groups = regression)
    public void testWriteShiftName() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://www.saucedemo.com/");

        final var faker = new Faker();
        final String fullName = faker.name().fullName();
        Logs.debug("Fullname: %s", fullName);

        final var fullNameInput = driver.findElement(By.id("user-name"));

        Logs.info("Presionando SHIFT y escribiendo en mayusculas");
        new Actions(driver)
                .click(fullNameInput)
                .keyDown(Keys.SHIFT)
                .sendKeys(fullName)
                .keyUp(Keys.SHIFT)
                .perform();

        Logs.info("Verificanod que el input este en mayusculas");
        Assert.assertEquals(
                fullNameInput.getAttribute("value"),
                fullName.toUpperCase()
        );

    }

    @Test(groups = regression)
    public void testWriteAddress() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://demoqa.com/text-box");

        final var faker = new Faker();
        final String address = faker.address().fullAddress();
        Logs.debug("Address: %s", address);

        final var currentAddressInput = driver.findElement(By.id("currentAddress"));

        Logs.info("Haciendo click para ganar focus y haciendo focus");

        new Actions(driver)
                .click(currentAddressInput)
                .sendKeys(address)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .sendKeys("c")
                .keyUp(Keys.CONTROL)
                .perform();

        final var permanentAddressInput = driver.findElement(By.id("permanentAddress"));

        Logs.info("Dando focus y pegando el contenido");
        new Actions(driver)
                .click(permanentAddressInput)
                .keyDown(Keys.CONTROL)
                .sendKeys("v")
                .keyUp(Keys.CONTROL)
                .perform();

        Logs.info("Verificando que ambos input tengan el mismo texto");
        Assert.assertEquals(
                permanentAddressInput.getAttribute("value"),
                currentAddressInput.getAttribute("value")
        );
    }

    @Test(groups = regression)
    public void testDropDragMe() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://demoqa.com/droppable");

        final var dragMeElement = driver.findElement(By.id("draggable"));
        final var dropElement = driver.findElement(By.id("droppable"));

        Logs.info("Arrastramos la figura de origen a la de destino");
        new Actions(driver)
                .dragAndDrop(dragMeElement, dropElement)
                .perform();

        Logs.info("Verificamos que el label de dropped este visible");
        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Dropped!']")).isDisplayed());
    }

    @Test(groups = regression)
    public void testHoverLabel() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://demoqa.com/tool-tips");

        final var greenButton = driver.findElement(By.id("toolTipButton"));

        Logs.info("Poniendo el cursor sobre el elemento");
        new Actions(driver)
                .moveToElement(greenButton)
                .pause(1500)
                .perform();

        Logs.info("Verificando el texto del hover");
        Assert.assertEquals(greenButton.getAttribute("aria-describedby"),
                "buttonToolTip"
        );
    }

    @Test
    public void testAlertAccept() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://demoqa.com/alerts");

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        Logs.info("Esperando que cargue la pagina");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[text()='Alerts']")));

        Logs.info("Haciendo click en el boton para que aparezca el alert");
        driver.findElement(By.id("alertButton")).click();

        Logs.debug("Obteniendo el alert");
        final var alert = (Alert) wait.until(ExpectedConditions.alertIsPresent());

        Logs.info("Verificando que el texto del alert sea correcto");
        Assert.assertEquals(alert.getText(), "You clicked a button");

        Logs.info("Presionando el boton del alert");
        alert.accept();
    }

    @Test
    public void testDismissAlert() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://demoqa.com/alerts");

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        Logs.info("Esperando que cargue la pagina");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[text()='Alerts']")));

        Logs.info("Haciendo click en el boton para que aparezca el alert");
        driver.findElement(By.id("confirmButton")).click();

        Logs.debug("Obteniendo el alert de confirm");
        final var alert = (Alert) wait.until(ExpectedConditions.alertIsPresent());

        Logs.info("Haciendo click en cancel");
        alert.dismiss();

        Logs.info("Verificando que aparece el mensaje del alert");
        Assert.assertTrue(driver.findElement(By.id("confirmResult")).isDisplayed());
    }

    @Test
    public void testAlertPrompt() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://demoqa.com/alerts");

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        Logs.info("Esperando que cargue la pagina");
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[text()='Alerts']")));

        Logs.info("Haciendo click en el boton para que aparezca el alert");
        driver.findElement(By.id("promtButton")).click();

        Logs.debug("Obteniendo el alert de prompt box");
        final var alert = (Alert) wait.until(ExpectedConditions.alertIsPresent());

        final var faker = new Faker();
        final var randomName = faker.name().firstName();

        Logs.info("Escribiendo el nombre aleatorio en el prompt: $s", randomName);
        alert.sendKeys(randomName);

        Logs.info("Presionando accept en el prompt");
        alert.accept();

        final var dynamicLocator = String.format("//span[text()='%s']", randomName);

        Assert.assertTrue(driver.findElement(By.xpath(dynamicLocator)).isDisplayed());
    }
}
