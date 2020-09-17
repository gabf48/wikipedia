package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class BasePage {
  public WebDriver driver;
  public WebDriverWait wait;

  // Constructor
  public BasePage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, 60);
  }

  // Wait Wrapper Method
  public void waitVisibility(By elementBy) {
    wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
  }

  // Wait Disappear Element
  public void waitDisappearsElement(By element) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, 40);
      WebElement loadingElement = driver.findElement(element);
      waitVisibility((By) loadingElement);
      wait.until(ExpectedConditions.invisibilityOf((loadingElement)));
    } catch (Exception ignored) {
    }
  }

  public void waitDisappearsLoadingSign() {
    wait = new WebDriverWait(driver, 30);
    //old db
    try {
      wait.until(
              ExpectedConditions.visibilityOfAllElementsLocatedBy(
                      (By.cssSelector("[class=\"lds-dual-ring-container ng-star-inserted\"]"))));
      WebElement loadingElement =
              driver.findElement(
                      By.cssSelector("[class=\"lds-dual-ring-container ng-star-inserted\"]"));
      wait.until(ExpectedConditions.invisibilityOf((loadingElement)));
    } catch (Exception ignored) {

    }

  }

  // Click Method
  public void click(By elementBy) {
    waitVisibility(elementBy);
    driver.findElement(elementBy).click();
  }

  // Write Text
  public void writeText(By elementBy, String text) {
    waitVisibility(elementBy);
    driver.findElement(elementBy).sendKeys(text);
  }

  // Read Text
  public String readText(By elementBy) {
    waitVisibility(elementBy);
    return driver.findElement(elementBy).getText();
  }

  // Read Attribute
  public String readAttribute(By elementBy, String attribute) {
    waitVisibility(elementBy);
    return driver.findElement(elementBy).getAttribute(attribute);
  }

  // Assert
  public void assertEquals(By elementBy, String expectedText) {
    waitVisibility(elementBy);
    Assert.assertEquals(readText(elementBy), expectedText);
  }

  // Scroll
  public void scrollUpPage() {
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("scroll(0, -550);");
  }

  public void scrollDownPage() {
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("scroll(0, 500);");
  }

  // clear
  public void clearText(By elementBy) {
    waitVisibility(elementBy);
    driver.findElement(elementBy).clear();
  }

  // language
  public void setLanguage(String language) {
    List<WebElement> categoryList =
        driver.findElements(By.cssSelector("[class=\"btn-group m-1\"] .btn"));
    for (WebElement e : categoryList) {
      if (e.getText().contains(language)) {
        e.click();
        break;
      }
    }
  }

  public void selectClient(String client) {
    waitDisappearedLoadingBar();
    driver.findElement(By.cssSelector(".dropdown-toggle")).click();
    List<WebElement> categoryList = driver.findElements(By.cssSelector(".dropdown-item"));
    for (WebElement e : categoryList) {
      if (e.getText().contains(client)) {
        e.click();
        break;
      }
    }
    waitDisappearedLoadingBar();
    hoverLogOutButton();
  }

  public void assertClient(String client){
        wait.until(
        ExpectedConditions.textToBePresentInElement(
            driver.findElement(By.cssSelector(".dropdown-toggle")), client));
  }

  private void hoverLogOutButton() {
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(By.id("logout-button"))).build().perform();
  }

  // new staging
  public void waitDisappearedLoadingBar() {
    try {
      waitVisibility(By.cssSelector("[class=\"fas fa-sync-alt spin spinner\"]"));
      wait.until(
          ExpectedConditions.invisibilityOf(
              (driver.findElement(By.cssSelector("[class=\"fas fa-sync-alt spin spinner\"]")))));
    } catch (Exception ignored){}
  }

  //assert an element is not displayed
  public void assertTheButtonIsMissing(String button){
    boolean presence = true;
    try {
      if (driver.findElement(By.cssSelector(button)).isDisplayed()) {
        presence = false;
      } } catch (Exception ignored){ }

    Assert.assertTrue(presence);
  }

  //assert an element is displayed
  public void assertTheButtonIsDisplayed(String button){
    boolean presence = false;
    try{
      if (driver.findElement(By.cssSelector(button)).isDisplayed()) {
        presence = true;
      }
    } catch (Exception ignored){}

    Assert.assertTrue(presence);
  }

  //assert if a text is displayed on the page
  public void assertIfTextIsDisplayedOnTheScreen(String text){
    boolean titleAppeared = false;

    if (driver.getPageSource().contains(text)) {
      titleAppeared = true;
    }
    Assert.assertTrue(titleAppeared);
  }

  //assert if a text is NOT displayed on the page
  public void assertIfTextIsNotDisplayedOnTheScreen(String text){
    boolean presence = false;
    try{
    if(driver.getPageSource().contains(text)){
      presence=true;
    }} catch (Exception ignored){}
    Assert.assertFalse(presence);
  }
}
