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


}
