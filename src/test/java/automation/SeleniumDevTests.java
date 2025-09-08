package automation;

import net.datafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class SeleniumDevTests extends BaseTest {

    @Test
    public void testScrollLine() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://www.selenium.dev/selenium/web/scroll.html");

        Logs.debug("Generamos un numero entre 5 y 9");
        final var faker = new Faker();
        final var number = faker.number().numberBetween(5, 9);
        Logs.debug("Number: %d", number);

        final var dynamicId = String.format("line%d", number);
        final var lineNumber = driver.findElement(By.id(dynamicId));

        Logs.info("Haciendo scroll hacia el id: %s", dynamicId);
        new Actions(driver)
                .scrollToElement(lineNumber)
                .pause(1000)
                .perform();

        Logs.info("Haciendo click en lineN");
        lineNumber.click();

        Logs.info("Verificando que el texto sea: %s", dynamicId);
        Assert.assertEquals(
                driver.findElement(By.id("clicked")).getText(),
                dynamicId
        );
    }

    @Test
    public void testScroll() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/page_with_frame_out_of_view.html");

        final var iframe = driver.findElement(By.name("frame"));

        Logs.info("Haciendo scroll hacia el iframe");
        new Actions(driver)
                .scrollToElement(iframe)
                .pause(1000)
                .perform();

        Logs.debug("Cambiamos el contexto ");
        driver.switchTo().frame(iframe);

        final var checkBox = driver.findElement(By.name("checkbox"));

        Logs.info("Haciendo click en el checkbox");
        checkBox.click();

        Logs.info("Verificando que el checkbox este checked");
        Assert.assertTrue(checkBox.isSelected());
    }
}
