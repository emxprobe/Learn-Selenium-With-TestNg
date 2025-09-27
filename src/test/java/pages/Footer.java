package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.BasePage;
import utilities.Logs;

public class Footer extends BasePage {

    private final By twitterButton = By.xpath("//a[text()='Twitter']");
    private final By facebookButton = By.xpath("//a[text()='Facebook']");
    private final By linkedinButton = By.xpath("//a[text()='LinkedIn']");

    @Override
    public void waitPageToLoad() {}

    @Override
    @Step("Verificando el footer")
    public void verifyPage() {

        Logs.info("Verificando el footer");
        softAssert.assertTrue(find(twitterButton).isDisplayed());
        softAssert.assertTrue(find(facebookButton).isDisplayed());
        softAssert.assertTrue(find(linkedinButton).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Verificando los links de las redes sociales")
    public void verifySocialMediaLink(
            String twitterLink,
            String facebookLink,
            String linkedinLink){

        Logs.info("Verificando que los links sean correctos");

        final WebElement twitterLabel = find(twitterButton);
        final WebElement facebookLabel = find(facebookButton);
        final WebElement linkedinLabel = find(linkedinButton);

        softAssert.assertTrue(twitterLabel.isDisplayed());
        softAssert.assertTrue(facebookLabel.isDisplayed());
        softAssert.assertTrue(linkedinLabel.isDisplayed());

        softAssert.assertTrue(twitterLabel.isEnabled());
        softAssert.assertTrue(facebookLabel.isEnabled());
        softAssert.assertTrue(linkedinLabel.isEnabled());

        softAssert.assertEquals(twitterLabel.getAttribute("href"), twitterLink);
        softAssert.assertEquals(facebookLabel.getAttribute("href"), facebookLink);
        softAssert.assertEquals(twitterLabel.getAttribute("href"), linkedinLink);

        softAssert.assertAll();
    }
}
