package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BasePage;

public class HomePage extends BasePage {

  // *********Constructor*********
  public HomePage(WebDriver driver) {
    super(driver);
  }

  // *********Web Elements*********
  By englishLanguage = By.id("js-link-box-en");

  public void openHomePage() {
    driver.navigate().to("https://www.wikipedia.org/");
    driver.manage().window().maximize();
    selectEnglishLanguage();
  }

  private void selectEnglishLanguage() {
    click(englishLanguage);
  }
}
