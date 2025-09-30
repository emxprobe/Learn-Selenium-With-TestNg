package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class ItemDetailPage extends BasePage {

    private final By itemDetailTitle = By.className("inventory_details_name");
    private final By bacToProductsButton = By.id("back-to-products");
    private final By itemDetailImage = By.className("inventory_details_img");
    private final By itemDetailPrice = By.className("inventory_details_price");
    private final By itemDetailDescription = By.className("inventory_details_desc");
    private final By addToCartButton = By.id("add-to-cart");

    @Override
    @Step("Esperando que cargue la pagina de Item Details")
    public void waitPageToLoad() {

        Logs.info("Esperando que cargue la pagina de Item Details");
        waitPage(this.itemDetailImage, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando la pagina de Item Details")
    public void verifyPage() {

        Logs.info("Verificando la pagina de Item Details");

        softAssert.assertTrue(find(itemDetailTitle).isDisplayed());
        softAssert.assertTrue(find(bacToProductsButton).isDisplayed());
        softAssert.assertTrue(find(itemDetailImage).isDisplayed());
        softAssert.assertTrue(find(itemDetailPrice).isDisplayed());
        softAssert.assertTrue(find(itemDetailDescription).isDisplayed());
        softAssert.assertTrue(find(addToCartButton).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Regresando a la pagina de products")
    public void backToProducts(){

        Logs.info("Regresando a la pagina de products");
        find(bacToProductsButton).click();
    }
}
