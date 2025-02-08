package pages.transactions;

import com.shaft.driver.SHAFT;
import com.shaft.gui.internal.locator.Locator;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utils.GeneralOperations;

@Slf4j
public class OutTransactionDraftPage {

  SHAFT.TestData.JSON testData = new SHAFT.TestData.JSON("outTransactionDraftData.json");
  private SHAFT.GUI.WebDriver driver;

  private By incomDestinationNumber = By.xpath(
      "//input[contains(@class, 'InboundDepNum') and contains(@class, 'txtDepartmentNumber')]");
  private By outDestinationName = By.xpath(
      "//input[contains(@class, 'InboundDepName') and contains(@class, 'txtDepartmentName')]");
  private By outDestinationFilter = By.xpath(
      "//span[contains(@class, 'input-group-text') and contains(@class, 'hyperlink')]");
  private By outDestinationModal = By.id("modalBody__dvDestinationTree");
  private By outDestinationEntry = By.xpath(
      "//div[contains(text(),\"" + testData.getTestData("outgoingDestination")
          + "\") and contains(@data-name,'DestinationId')]");
  private By outTransactionNumber = By.xpath("//div[contains(@class, 'transaction-number')]");
  private By isLinkCheckBox = By.id("Islink");
  private By subjectTextField = By.name("OutboundExternalBasicInfo.Subject");
  private By transactionNameTab = By.xpath("//li[contains(@id,'Names')]");
  private By civilIdNumber = By.id("TransactionName_CivilID");
  private By firstName = By.id("TransactionName_FirstName");
  private By sendSMSCheckBox = By.id("TransactionName_SendSMS");
  private By attachFileButton = By.id("Attachment");
  private By attachmentType = By.id("ddlAttachmentType");
  private By uploadAttachment = By.id("aTextEditor");
  private By uploadNewAttachmentButton = By.xpath(
      "//a[contains(@class,'btn-upload') and @href='#']");
  private By chooseAttachmentFile = By.id("TransactionFiles");
  private By addAttachmentButton = By.id("btnAddArchive");
  private By attachmentsTableRows = By.xpath(
      "//table[@id='grid-table-grdArchiving']//tr[contains(@class,'grid-row')]");
  private By uploadAttachmentContainer = By.id("uploadContainer");
  private By saveTransactionButton = By.id("btnSaveOutboundExternal");
  private By printTrxDeliveryReportButton = By.xpath(
      "//a[contains(@onclick,'PrintTransactionDeliveryReport')]");
  private By printBarcodeStickerButton = By.id("btnPrintBarcodeSticker");
  private By printBarcodeButton = By.id("btnPrintBarcode");
  private By confirmationAndSuccessModal = By.xpath(
      "//div[contains(@class, 'jconfirm') and contains(@class, 'white')]");
  private By printConfirmationModal = By.xpath(
      "//div[contains(@class, 'jconfirm') and contains(@class, 'white')]");
  private By confirmationPrintButton = By.xpath("//button[contains(@onclick,'PrintAllBarcode')]");
  private By printDialogue = By.xpath("//print-preview-app");
  private By confirmationCloseButton = By.xpath("//button[contains(@onclick,'dialog.close')]");
  private By confirmationDialogue = Locator.hasTagName("div")
      .containsText("هل أنت متأكد من عملية التعديل؟").insideShadowDom(confirmationAndSuccessModal)
      .build();
  private By confirmationAgreeButton = By.xpath("//button[contains(text(), 'نعم')]");
  private By confirmationCancelButton = By.xpath("//button[contains(text(), 'الغاء')]");
  private By successDialogue = Locator.hasTagName("button")
      .containsText("تمت عملية التعديل بنجاح").insideShadowDom(confirmationAndSuccessModal).build();
  private By successCloseButton = By.xpath("//button[contains(text(), \"إغلاق\")]");
  private By transactionNumberFromConfirmation = By.xpath(
      "//div[contains(@class,'col-xs-4')]/span[contains(@class,'indent-text')]");
  private By backToIncomTransactionButton = By.xpath(
      "//button[contains(@class, 'redirect-to-tray')]");
  private By printTransactionDeliveryReportFromPopup = By.xpath(
      "//button[contains(@class,'move-to-assignment')]");

  private String transactionNumber;
  @Getter
  private String transactionDescription;
  @Getter
  private int numberOfBrowserWindows;

  @Getter
  private boolean isPrintDialogueDisplayed;


  public OutTransactionDraftPage(SHAFT.GUI.WebDriver driver) {
    this.driver = driver;
  }

  @Step("تعديل موضوع المعاملة و اعطائه القيمة الحالية للوقت و التاريخ")
  public OutTransactionDraftPage modifyTransactionSubject() {
    driver.element().type(subjectTextField,
        "Description: " + GeneralOperations.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
    driver.element().scrollToElement(saveTransactionButton).click(saveTransactionButton)
        .verifyThat(confirmationAndSuccessModal).isVisible();
    driver.element().click(confirmationAgreeButton).verifyThat(confirmationAndSuccessModal)
        .isVisible().perform();
    driver.element().verifyThat(confirmationAndSuccessModal).doesNotExist();
    return this;
  }

  @Step("اضافة ملحقات")
  public void addAttachmentToTransaction(String transactionAttachmentType,
      String transactionAttachmentLocation) {
    driver.element().click(attachFileButton).select(attachmentType, transactionAttachmentType)
        .captureScreenshot(attachmentType);
    showSelectAttachment();
    driver.element()
        .click(uploadAttachment)
        .typeFileLocationForUpload(chooseAttachmentFile, transactionAttachmentLocation)
        .click(addAttachmentButton)
        .verifyThat(uploadAttachmentContainer).attribute("style").contains("none;");
  }

  private void showSelectAttachment() {
    WebDriver wDriver = driver.getDriver();
    ((JavascriptExecutor) wDriver)
        .executeScript("arguments[0].classList.remove('hidden');",
            wDriver.findElement(chooseAttachmentFile));
  }

  @Step("حفظ التعديل")
  public OutTransactionDraftPage saveModifiedTransaction() {
    driver.element().scrollToElement(saveTransactionButton).click(saveTransactionButton)
        .verifyThat(confirmationAndSuccessModal).isVisible();
    driver.element().click(confirmationAgreeButton).verifyThat(confirmationAndSuccessModal)
        .isVisible().perform();
    // driver.element().verifyThat(confirmationAndSuccessModal).doesNotExist();
    //ToDo
        /*
        Optimize this part to be used for all the save actions
        https://github.com/ShaftHQ/SHAFT_ENGINE/issues/1834
         */
    return this;
  }

  @Step("طباعة بيان التسليم")
  public OutTransactionDraftPage printTransactionDeliveryReport() {
    driver.element().scrollToElement(printTrxDeliveryReportButton)
        .click(printTrxDeliveryReportButton)
        .verifyThat(printTrxDeliveryReportButton).isEnabled();
    saveModifiedTransaction();
    return this;
  }

  @Step("طباعة رمز التشفير - ملصق")
  public OutTransactionDraftPage printBarcodeSticker() {
    driver.element().scrollToElement(printBarcodeStickerButton)
        .click(printBarcodeStickerButton)
        .verifyThat(printConfirmationModal).isVisible();
    // driver.browser().captureScreenshot();
    driver.element().click(confirmationPrintButton)
        .waitToBeReady(confirmationPrintButton, true);
    driver.element().click(confirmationCloseButton);
    // saveModifiedTransaction();
    return this;
  }

  @Step("طباعة رمز التشفير")
  public OutTransactionDraftPage printBarcode() {
    String originalWindowHandle = driver.getDriver().getWindowHandle();
    Allure.step("OriginalWindow: " + originalWindowHandle);
    driver.element().scrollToElement(printBarcodeButton)
        .click(printBarcodeButton)
        .verifyThat(printConfirmationModal).isVisible();
    // driver.browser().captureScreenshot();
    driver.element().click(confirmationPrintButton)
        .waitToBeReady(confirmationPrintButton, true);
    numberOfBrowserWindows = driver.browser().getWindowHandles().size();

    // Switch to the new window
    List<String> allWindows = driver.browser().getWindowHandles();
    for (String windowHandle : allWindows) {
      if (!windowHandle.equals(originalWindowHandle)) {
        driver.browser().switchToWindow(windowHandle);
        break;
      }
    }
    if (driver.browser().getCurrentURL().contains("print")
        || driver.browser().getCurrentWindowTitle().contains("Barcode")) {
      isPrintDialogueDisplayed = true;
    } else {
      isPrintDialogueDisplayed = false;
    }
    //driver.browser().switchToWindow(originalWindowHandle).element().click(confirmationCloseButton);
    // saveModifiedTransaction();
    return this;
  }

  @Step("عدد الملحقات")
  public int getNumberOfAttachmentsInGrid() {
    return driver.element().getElementsCount(attachmentsTableRows);
  }

  @Step("اضافة صادر خارجى جديد")
  public OutTransactionDraftPage addGeneralTransaction() {
    driver.element().click(isLinkCheckBox);
    driver.element().click(outDestinationFilter).verifyThat(outDestinationModal).isVisible();
    driver.element().click(outDestinationEntry)
        .verifyThat(outDestinationName).text()
        .contains(testData.getTestData("outgoingDestination"));
    driver.element().type(subjectTextField,
        "Description: " + GeneralOperations.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
    transactionDescription = driver.element().getText(subjectTextField);
    driver.element().click(transactionNameTab)
//To be un-commented when needed to send SMS
//                .click(sendSMSCheckBox)
        .type(civilIdNumber, testData.getTestData("civilId"))
        .keyPress(civilIdNumber, Keys.ENTER)
        .verifyThat(firstName).text().isNotNull();
    driver.element().scrollToElement(saveTransactionButton).click(saveTransactionButton)
        .verifyThat(confirmationAndSuccessModal).isVisible();
    driver.element().click(confirmationAgreeButton).verifyThat(confirmationAndSuccessModal)
        .isVisible().perform();
    transactionNumber = driver.element().getText(transactionNumberFromConfirmation);
    return this;
  }

  @Step("الرجوع الى صفحة المعاملات الصادرة")
  public OutTransactionsPage backToOutgoingTransactionPage() {
    driver.element().click(backToIncomTransactionButton);
    return new OutTransactionsPage(driver);
  }

  @Step("الحصول على رقم المعاملة اثناء الاضافة")
  public String getTransactionNumberFromConfirmation() {
    return transactionNumber;
  }

  @Step("طباعى بيان التسليم من الشاشة المنبثقة")
  public OutTransactionDraftPage printTransactionDeliveryReportFromConfirmation() {
    driver.element().click(printTransactionDeliveryReportFromPopup)
        .browser().waitUntilNumberOfWindowsToBe(2).waitUntilNumberOfWindowsToBe(1);
    return this;
  }

  @Step("الحصول على رقم المعاملة من صفحة التعديل")
  public String getOutTransactionNumber() {
    return driver.element().getText(outTransactionNumber);
  }

  @Step("العودة لصفحة المعاملات الصادرة")
  public OutTransactionsPage navigateToTransactionsPage() {
    driver.browser().navigateBack();
    return new OutTransactionsPage(driver);
  }

  @Step("الحصول على موضوع المعاملة من صفحة التعديل")
  public String getTransactionSubject() {
    return driver.element().getText(subjectTextField);
  }

}
