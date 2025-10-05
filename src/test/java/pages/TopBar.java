package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class TopBar extends BasePage {

    private final By logo = By.xpath("//div[text()='Swag Labs']");
    private final By shoppingCartLink = By.className(".shopping_cart_link");
    private final By burgerMenuButton = By.id("react-burger-menu-btn");

    @Override
    public void waitPageToLoad() {}

    @Override
    @Step("Verificando el top bar")
    public void verifyPage() {

        Logs.info("Verificando el top bar");
        softAssert.assertTrue(find(logo).isDisplayed());
        softAssert.assertTrue(find(shoppingCartLink).isDisplayed());
        softAssert.assertTrue(find(burgerMenuButton).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Abriendo el burguer menu")
    public void openBurgerMenu() {

        Logs.info("Abriendo el burguer menu");
        find(burgerMenuButton).click();
    }

    @Step("Haciendo click en el boton de shopping cart")
    public void clickShoppingCart(){

        Logs.info("Haciendo click en el boton de shopping cart");
        find(shoppingCartLink).click();
    }
}
