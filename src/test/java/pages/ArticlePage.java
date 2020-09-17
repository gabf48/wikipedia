package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utils.BasePage;

public class ArticlePage extends BasePage {

    // *********Constructor*********
    public ArticlePage(WebDriver driver) {
        super(driver);
    }

    // *********Web Elements*********
    By articleTitle = By.className("firstHeading");

    private String storeArticleTitle(){
        return readText(articleTitle);
    }

    public void assertIfTheTitleContainSpecificWord(String word){
        boolean presence = false;
        if(storeArticleTitle().contains(word)){
            presence = true;
      System.out.println("The article's title contain the word :" + word);
        } else {
      System.out.println("Sorry, but the article's title don't contain your searched word!");
        }

        Assert.assertTrue(presence);
    }
}
