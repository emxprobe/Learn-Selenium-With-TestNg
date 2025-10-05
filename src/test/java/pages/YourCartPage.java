package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.bidi.log.Log;
import utilities.BasePage;
import utilities.Logs;

public class YourCartPage extends BasePage {

    private final By checkoutButton = By.id("checkout");

    @Override
    @Step("Esperando que la pagina de Your cart cargue")
    public void waitPageToLoad() {

        waitPage(checkoutButton, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando la pagina de Your cart")
    public void verifyPage() {

        Logs.info("Verificando la pagina de Your cart");
        softAssert.assertTrue(find(checkoutButton).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Haciendo click en el boton de checkout")
    public void clickOnCheckout(){

        Logs.info("Haciendo click en el boton de checkout");
        find(checkoutButton).click();
    }
}
