package pages.transactions;

import com.shaft.driver.SHAFT;
import components.HorizontalMenusComponent;
import components.TransactionsNavigationPanelComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class BriefcasePage extends TransactionsNavigationPanelComponent {

  private By transactionNumberField = By.id("TransNO");
  private By searchButton = By.cssSelector(".btn.btn-st1");
  private By loadingSpinner = By.id("loadingModal");
  private By transactionNumberOnCard = By.cssSelector(".pr-1");

  public BriefcasePage(SHAFT.GUI.WebDriver driver) {
    super(driver, new HorizontalMenusComponent(driver));
  }

  @Step("البحث عن معاملة")
  public BriefcasePage searchForTransaction(String transactionNo) {
    driver.element().type(transactionNumberField, transactionNo)
        .click(searchButton)
        .verifyThat(loadingSpinner).isVisible();
    return this;
  }

  @Step("رقم المعاملة على كارت العرض")
  public String getTransactionNumberFromCard() {
    return driver.element().getText(transactionNumberOnCard);
  }
}
