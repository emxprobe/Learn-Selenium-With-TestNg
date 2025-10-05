package pages;

import io.qameta.allure.Step;
import models.ItemProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;
import utilities.BasePage;
import utilities.Logs;

import java.util.List;

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

    private By getItemName(String itemName){

        final var xpathFormat = String.format("//div[text()='%s']", itemName);
        return By.xpath(xpathFormat);
    }

    private By getProductPrice(String itemName){

        return RelativeLocator
                .with(By.className("inventory_item_price"))
                .below(getItemName(itemName));
    }

    @Step("Navegando al producto")
    public void goToItemDetail(String itemName){

        Logs.info("Navegando al producto");
        find(getItemName(itemName)).click();
    }

    @Step("Verificando el precio de los productos")
    public void verifyProductPrice(List<ItemProduct> itemsList){

        Logs.info("Verificando el precio de los productos");

        for (var item : itemsList) {

            final var priceLabel = find(getProductPrice(item.getName()));
            final var price = Double.parseDouble(priceLabel.getText().replace("$", ""));

            softAssert.assertEquals(
                    price,
                    item.getPrecio(),
                    String.format("Fallo: %s", item.getName())
            );
        }
        softAssert.assertAll();
    }
}
