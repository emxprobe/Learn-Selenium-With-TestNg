package utilities;

import data.DataGiver;
import org.openqa.selenium.WebDriver;
import pages.BurgerMenu;
import pages.ItemDetailPage;
import pages.LoginPage;
import pages.ShoppingPage;
import pages.TopBar;

public class CommonFlows {

    private WebDriver getDriver() {

        return new WebdriverProvider().get();
    }

    public void goToLoginPage(){

        Logs.info("Navegando a la url");
        getDriver().get("https://www.saucedemo.com/");

        new LoginPage().waitPageToLoad();
    }

    public void goToShoppingPage() {

        final var validCredential = DataGiver.getValidCredentials();

        goToLoginPage();

        new LoginPage().fillLogin(
                validCredential.getUsername(),
                validCredential.getPassword());

        new ShoppingPage().waitPageToLoad();
    }

    public void openBurgerMenu() {

        goToShoppingPage();

        new TopBar().openBurgerMenu();
        new BurgerMenu().waitPageToLoad();
    }

    public void goToItemDetail(String itemName){

        goToShoppingPage();

        new ShoppingPage().goToItemDetail(itemName);
        new ItemDetailPage().waitPageToLoad();
    }
}
