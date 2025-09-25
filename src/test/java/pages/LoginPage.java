package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class LoginPage extends BasePage {

    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorLabel = By.cssSelector("h3[data-test='error']");

    @Override
    @Step("Esperando que la pagina cargue")
    public void waitPageToLoad() {

        waitPage(this.usernameInput, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando la pagina de login")
    public void verifyPage() {

        Logs.info("Verificando la pagina de login");
        softAssert.assertTrue(find(usernameInput).isDisplayed());
        softAssert.assertTrue(find(passwordInput).isDisplayed());
        softAssert.assertTrue(find(loginButton).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Rellenando el formulario de login")
    public void fillLogin(String username, String password){

        Logs.info("Escribiendo el username");
        find(usernameInput).sendKeys(username);

        Logs.info("Escribiendo el password");
        find(passwordInput).sendKeys(password);

        Logs.info("Haciendo click en el boton de login");
        find(loginButton).click();
    }

    @Step("Verificando el mensaje de error")
    public void verifyErrorMessage(String errorMessage){

        final var errorText = find(errorLabel);

        Logs.info("Verificando el mensaje de error");
        softAssert.assertTrue(find(errorLabel).isDisplayed());
        softAssert.assertEquals(errorText.getText(), errorMessage);
        softAssert.assertAll();
    }
}
