package utilities;

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

        goToLoginPage();

        new LoginPage().fillLogin("standard_user", "secret_sauce");
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
