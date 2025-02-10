package pages.transactions;

import com.shaft.driver.SHAFT;
import components.HorizontalMenusComponent;
import components.SystemAdminComponent;
import components.TransactionsNavigationPanelComponent;
import components.TransactionsOperationsComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

public class TransactionsFollowupPage extends TransactionsNavigationPanelComponent {

  private By firtFollowupCardNumber = By.xpath(
      "(//div[contains(@class,'box-grid')]//div[contains(@class,'pr-1')])[1]");
  private By numberOfAttachmentsAtFirstFollowup = By.xpath(
      "(//div[contains(@class,'ml-auto')]/div[@class='info']//span)[1]");
  private By dateOnTheFirstCard = By.xpath(
      "(//div[contains(text(),'تاريخ المتابعة ')]/following-sibling::div[@class='result'])[1]");
  private By sentFollowupRequestsTab = By.id("SentFollowUpTab");


  @Getter
  private TransactionsOperationsComponent transactionsOperationsComponent;
  @Getter
  private SystemAdminComponent systemAdminComponent;

  public TransactionsFollowupPage(SHAFT.GUI.WebDriver driver) {
    super(driver, new HorizontalMenusComponent(driver));
    transactionsOperationsComponent = new TransactionsOperationsComponent(driver);
    systemAdminComponent = new SystemAdminComponent(driver);
  }

  @Step("الذهاب الى تبويب متابعات الادارة")
  public TransactionsFollowupPage navigateToSentFollowUpTab() {
    driver.element().click(sentFollowupRequestsTab);
    driver.browser().refreshCurrentPage();
    return this;
  }

  @Step("الحصول على رقم المتابعة الأولى")
  public String getFirstTransactionNumber() {
    return driver.element().getText(firtFollowupCardNumber);
  }

  @Step("الحصول على عدد ملحقات المعاملة الأولى")
  public int getNumberOfAttachmentsOnFirstCard() {
    driver.element().scrollToElement(numberOfAttachmentsAtFirstFollowup);
    String trimmedNumber = driver.element().getText(numberOfAttachmentsAtFirstFollowup)
        .replaceAll("[^0-9]", "");
    return Integer.parseInt(trimmedNumber);
  }

  @Step("الحصول على تاريخ المتابعة الأولى")
  public String getFirstTransactionDate() {
    return driver.element().getText(dateOnTheFirstCard);
  }

}
