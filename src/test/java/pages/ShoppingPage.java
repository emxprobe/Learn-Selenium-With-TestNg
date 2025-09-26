package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class ShoppingPage extends BasePage {

    private final By title = By.xpath("//span[text()='Products']");
    private final By itemSelect = By.cssSelector("select[data-test='product-sort-container']");
    private final By itemsList = By.className("inventory_list");

    @Override
    @Step("Esperando que cargue la pagina de Shopping")
    public void waitPageToLoad() {

        waitPage(this.itemsList, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando la pagina de Shopping")
    public void verifyPage() {

        Logs.info("Verificando la pagina");
        softAssert.assertTrue(find(title).isDisplayed());
        softAssert.assertTrue(find(itemSelect).isDisplayed());
        softAssert.assertTrue(find(itemsList).isDisplayed());
        softAssert.assertAll();
    }
}
