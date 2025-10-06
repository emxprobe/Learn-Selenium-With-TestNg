package utilities;

import data.DataGiver;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pages.BurgerMenu;
import pages.ItemDetailPage;
import pages.LoginPage;
import pages.ShoppingPage;
import pages.TopBar;
import pages.YourCartPage;
import pages.YourInformationPage;

public class CommonFlows {

    private WebDriver getDriver() {

        return new WebdriverProvider().get();
    }

    public void goToLoginPage(){

        Logs.info("Navegando a la url");
        getDriver().get("https://www.saucedemo.com/");

        new LoginPage().waitPageToLoad();
    }

    private void assignLoginCookie(){

        Logs.debug("Asignando la cookie de login");
        getDriver().get("https://www.saucedemo.com/404");

        final var validCredentials = DataGiver.getValidCredentials();
        final var loginCookie =
                new Cookie("session-username", validCredentials.getUsername());
        getDriver().manage().addCookie(loginCookie);
    }

    public void goToShoppingPage() {

        assignLoginCookie();
        getDriver().get("https://www.saucedemo.com/inventory.html");

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

    public void goToYourCartPage(){

        goToShoppingPage();
        new TopBar().clickShoppingCart();
        new YourCartPage().waitPageToLoad();
    }

    public void goToYourInformationPage(){

        goToYourCartPage();
        new YourCartPage().clickOnCheckout();
        new YourInformationPage().waitPageToLoad();

    }
}
