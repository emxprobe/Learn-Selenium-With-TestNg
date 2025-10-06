package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.bidi.log.Log;
import utilities.BasePage;
import utilities.Logs;

public class YourInformationPage extends BasePage {

    private final By firstnameInput = By.id("first-name");
    private final By lastnameInput = By.id("last-name");
    private final By postalCodeInput = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorLabel = By.cssSelector("h3[data-test='error']");

    @Override
    @Step("Esperando que cargue la pagina de Your Information")
    public void waitPageToLoad() {


        waitPage(this.continueButton, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando la pagina de Your Information")
    public void verifyPage() {

        Logs.info("Verificando la pagina de Your Information");
        softAssert.assertTrue(find(firstnameInput).isDisplayed());
        softAssert.assertTrue(find(lastnameInput).isDisplayed());
        softAssert.assertTrue(find(postalCodeInput).isDisplayed());
        softAssert.assertTrue(find(continueButton).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Rellenando el formulario")
    public void fillData(String firstname, String lastname, String zipcode){

        if (!firstname.isEmpty()){

            Logs.info("Escribiendo el firstname");
            find(firstnameInput).sendKeys(firstname);
        }

        if (!lastname.isEmpty()){

            Logs.info("Escribiendo el lastname");
            find(lastnameInput).sendKeys(lastname);
        }

        if (!zipcode.isEmpty()){

            Logs.info("Escribiendo el zipcode");
            find(postalCodeInput).sendKeys(zipcode);
        }

        Logs.info("Haciendo click en continue");
        find(continueButton).click();
    }

    @Step("Verificando el mensaje de error")
    public void verifyErrorMessage(String errorMessage){

        Logs.info("Verificando el mensaje de error");
        final var errorLabelElement = find(errorLabel);

        softAssert.assertTrue(errorLabelElement.isDisplayed());
        softAssert.assertEquals(errorLabelElement.getText(), errorMessage);
        softAssert.assertAll();
    }
}
