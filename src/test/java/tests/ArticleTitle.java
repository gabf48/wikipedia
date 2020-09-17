package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ArticlePage;
import pages.HomePage;
import pages.LeftMenuButtons;
import utils.BaseTest;

public class ArticleTitle extends BaseTest {

    private HomePage homePage;
    private LeftMenuButtons leftMenuButtons;
    private ArticlePage articlePage;

    @BeforeMethod
    public void setUp() {
        WebDriver driver = initializeDriver();
        homePage = new HomePage(driver);
        leftMenuButtons = new LeftMenuButtons(driver);
        articlePage = new ArticlePage(driver);
    }

  @Test(description = "Open a random article and check if the title contain \"a\" letter")
  public void openRandomArticleAndCheckIfTheTitleContainSpecificLetter() {
        homePage.openHomePage();
        leftMenuButtons.openRandomArticle();
        articlePage.assertIfTheTitleContainSpecificWord("a");


    }
}
