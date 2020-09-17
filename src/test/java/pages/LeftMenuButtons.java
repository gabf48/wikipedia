package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BasePage;

public class LeftMenuButtons extends BasePage {

    // *********Constructor*********
    public LeftMenuButtons(WebDriver driver) {
        super(driver);
    }

    // *********Web Elements*********
    By randomArticleButton = By.cssSelector("#n-randompage a");

    public void openRandomArticle(){
        click(randomArticleButton);
    }
}
