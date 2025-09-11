package automation;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

import java.io.File;

public class TestPagesTests extends BaseTest {

    @Test(groups = regression)
    public void testImageUpload() {

        Logs.info("Navegamos a la pagina");
        driver.get("https://testpages.eviltester.com/styled/file-upload-test.html");

        final var file = new File("src/test/resources/images/360_F_143428338_gcxw3Jcd0tJpkvvb53pfEztwtU9sxsgT.jpg");

        Logs.info("Subimos la imagen al sitio");
        driver.findElement(By.id("fileinput")).sendKeys(file.getAbsolutePath());

        Logs.info("Haciendo click en el radio button");
        driver.findElement(By.id("itsanimage")).click();

        Logs.info("Haciendo click en el buton de upload");
        driver.findElement(By.name("upload")).click();

        Logs.info("Verificando que se subio la imagen");
        Assert.assertTrue(
                driver.findElement(By.xpath("//h2[text()='You uploaded this image:']"))
                        .isDisplayed()
        );

    }
}
