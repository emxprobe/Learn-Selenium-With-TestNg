package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.BasePage;
import utilities.Logs;

import java.time.Duration;

public class BurgerMenu extends BasePage {

    private final By allItemsLink = By.id("inventory_sidebar_link");
    private final By aboutLink = By.id("about_sidebar_link");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By resetAppLink = By.id("reset_sidebar_link");

    @Override
    @Step("Esperando que cargue el burger menu")
    public void waitPageToLoad() {

        waitPage(this.logoutLink, this.getClass().getSimpleName());

        Logs.info("Esperando que sea clickeable por la animacion");
        final var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
    }

    @Override
    @Step("Verificando el burger menu")
    public void verifyPage() {

        Logs.info("Verificando el burger menu");
        softAssert.assertTrue(find(allItemsLink).isDisplayed());
        softAssert.assertTrue(find(aboutLink).isDisplayed());
        softAssert.assertTrue(find(logoutLink).isDisplayed());
        softAssert.assertTrue(find(resetAppLink).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Haciendo click en logout")
    public void clickLogout(){

        Logs.info("Haciendo click en logout");
        find(logoutLink).click();
    }
}