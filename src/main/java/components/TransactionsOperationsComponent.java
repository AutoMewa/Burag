package components;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.transactions.InTransactionDraftPage;
import pages.transactions.OutTransactionDraftPage;

public class TransactionsOperationsComponent {

  protected SHAFT.GUI.WebDriver driver;

  private By addGeneralIncomingTransaction = By.xpath(
      "//a[@href='/MCS.UI.Tenants/User/OutboundExternal/Add']");
  private By addGeneralIncomingTransactionButton = By.xpath(
      "//a[@href='/MCS.UI.Tenants/User/Inbound/Add']");
  private By filterButton = By.className("icon-filter");
  private By filterTransactionId = By.id("TransId");
  private By filterTransactionNumber = By.id("TransNo");
  private By filterSearchButton = By.xpath("//input[@type='button']");
  private By loadingSpinner = By.id("loadingModal");


  public TransactionsOperationsComponent(SHAFT.GUI.WebDriver driver) {
    this.driver = driver;
  }


  @Step("إنشاء صادر عام")
  public OutTransactionDraftPage addNewGeneralTransaction() {
    driver.element().click(addGeneralIncomingTransaction);
    return new OutTransactionDraftPage(driver);
  }

  @Step("البحث عن معاملة باستخدام رقم المعاملة")
  public <T> T searchForTransactionWithId(String transactionId, T pageObject) {
    driver.element().waitToBeReady(filterButton).click(filterButton);
    By inputField = driver.element().getElementsCount(filterTransactionId) > 0 ? filterTransactionId
        : filterTransactionNumber;
    driver.element().type(inputField, transactionId);
    driver.element()
       // .clickUsingJavascript(filterSearchButton)
        .click(filterSearchButton)
        .waitToBeInvisible(loadingSpinner)
        .verifyThat(filterSearchButton).isEnabled();
    return pageObject;
  }

  @Step("اضافة وارد جديد")
  public InTransactionDraftPage addNewIncomingTransaction() {
    driver.element().click(addGeneralIncomingTransactionButton);
    return new InTransactionDraftPage(driver);
  }

}
