package pages.transactions;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.*;
import utils.GeneralOperations;

import java.util.ArrayList;
import java.util.List;

public class InTransactionDraftPage {

    SHAFT.TestData.JSON testData = new SHAFT.TestData.JSON("inTransactionDraftData.json");
    private SHAFT.GUI.WebDriver driver;

    private By incomingTransactionNumber = By.xpath("//div[contains(@class, 'transaction-number')]");
    private By basicInfoTab = By.id("basicInfo");

    //=============================TransactionCopies==========================================
    private By transactionCopiesTab = By.id("transactionCopies");
    private By orgnaisationalUnitNumber = By.xpath("//input[@data-func='GetCopyUsersByOrgUnitId' and @onkeypress='return IsNumeric(event);']");
    private By orgUnitAutoCompleteMenu = By.cssSelector("#divAutoComplateMenu");
    private By firstOrgChartAutoSuggestion = By.cssSelector("#divAutoComplateMenu div:nth-of-type(1)");
    private By copyActionReason = By.id("ddlCopyActionId");
    private By firstAttachmentCheckBox = By.xpath("(//input[contains(@id,'mainDocumentCheckbox')])[2]");
    private String attachmentCheckBoxAtIndex = "(//input[contains(@id,'mainDocumentCheckbox')])[%s]";
    private String attachmentCopyTransactionDescriptionAtIndex = "(//td[@data-name='ArcivingTypeName'])[%s]";
    @Getter
    private List<String> attachmentCopyDescription = new ArrayList<>();
    private By addCopyButton = By.id("btnCopy");
    private By copyRowInGrid = By.xpath("//table[@id='grid-table-grdCopies']//tr[contains(@class,'grid-row')]");
    //===============================================================================================
    //================================TransactionAttachments==========================================
    private By attachmentsTab = By.id("anchorArchiving");
    private By attachmentType = By.id("ddlAttachmentType");
    private By attachmentVailidty = By.id("ddlDocumentValidityId");
    private By uploadAttachment = By.id("aTextEditor");
    private By addAttachmentButton = By.id("btnAddArchive");
    private By chooseAttachmentFile = By.id("TransactionFiles");
    private By uploadAttachmentContainer = By.id("uploadContainer");
    //===========================================================================
    //=========================================BasicInfo=========================
    private By inDataTypeEntities = By.id("Entities");
    private By inDataTypeIndividual = By.id("Individual");
    private By inDestinationName = By.xpath("//input[contains(@class, 'InboundDepName') and contains(@class, 'txtDepartmentName')]");
    private By inboundDocumentNumber = By.id("InboundBasicInfo_InboundDocumentNumber");
    private By subjectTextField = By.id("txtAreaSubject");
    private By transactionSubjectTextField = By.id("InboundBasicInfoEdit_Remarks");
    private By inTransactionDateCalendar = By.id("HasInboundDateCal");
    private By inTransactionPriorityLevel = By.id("ddlPriorityLevel");
    private By saveModifiedTransactionButton = By.id("btnEditInbound");
    private By saveInTransactionButton = By.id("btnSaveInbound");
    private By inDestinationFilter = By.xpath("//span[contains(@class, 'input-group-text') and contains(@class, 'hyperlink')]");
    private By inDestinationModal = By.id("modalBody__dvDestinationTree");
    private By outDestinationEntry = By.xpath("//div[contains(text(),\"" + testData.getTestData("incomingDestination") + "\") and contains(@data-name,'DestinationId')]");
    private By isLinkCheckBox = By.id("Islink");
    private By showNamesButton = By.id("btnShowNames");
    //=================================================================================================
    //========================================TransactionLetter=========================================
    private By transactionLetterFrame = By.id("frameViewer");
    private By trasnactionLetterUploadButton = By.id("OpenUpload");
    private By transactionLetterUploadModal = By.xpath("//div[@id='myModal' and contains(@style,'display')]");
    private By transactionLetterUploadArea = By.className("dz-message");
    private By transactionLetterUploadHiddenButton = By.xpath("//input[@type='file' and @class='dz-hidden-input']");
    private By transactionLettersThumbnail = By.xpath("//input[contains(@class,'docThumbFocussed')]");
    private By transactionExitButton = By.xpath("//div[@id='myModal']//button[@type='button' and contains(@class,'btn-st1')]");
    private By baseTransactionAttachmentsButton = By.id("btnShowAttachment");
    //==================================================================================================================
    //=======================================General/Generic_Fields=====================================================
    private By civilIdNumber = By.xpath("//input[@id='TransactionName_CivilID' and @data-val='true']");
    private By firstName = By.xpath("//input[@id='TransactionName_FirstName' and @data-val='true']");
    private By sendSMSCheckBox = By.xpath("//input[@id='TransactionName_SendSMS' and @data-val='true']");
    private By addNameButton = By.xpath("(//button[@id='btnNames'])[1]");
    private By loadingSpinner = By.id("loadingModal");
    private By namesGrid = By.xpath("//tr[contains(@class,'grid-row')]");
    //==================================================================================================================
    //====================================TransactionFollowup===========================================================
    private By followupTab = By.id("transactionFollowUp");
    private By followupOrgUnitNumber = By.xpath("(//input[@data-func='GetFollowUpUsersByOrgUnitId' and contains(@class,'txtDepartmentNumber')])[1]");
    private By followupOrgUnitAutoCompleteMenu = By.cssSelector("#divAutoComplateMenu");
    private By followupFirstOrgChartAutoSuggestion = By.cssSelector("#divAutoComplateMenu div:nth-of-type(1)");
    private By addTransactionFollowupButton = By.id("btnConfirmAddFollowUp");
    private By transactionFollowupRequestsGrid = By.id("grid-table-grdFollowUps");
    private By transactionFollowupGridRow = By.xpath(" //table[@id='grid-table-grdFollowUps']//tr[contains(@class,'grid-row')]");
    private By followupConfirmationAndSuccessModal = By.xpath("//div[contains(@class, 'jconfirm') and contains(@class, 'white')]");
    private By confirmAddFollowup = By.xpath("//div[contains(@class,'actions-buttons')]/button[1]");
    //==================================================================================================================
    //====================================Confirmation_Popup============================================================
    private By attachmentsTableRows = By.xpath("//table[@id='grid-table-grdArchiving']//tr[contains(@class,'grid-row')]");
    private By confirmationAndSuccessModal = By.xpath("//div[contains(@class, 'jconfirm') and contains(@class, 'white')]");
    private By confirmationAgreeButton = By.xpath("//button[contains(text(), 'نعم')]");
    private By confirmationCancelButton = By.xpath("//button[contains(text(), 'الغاء')]");
    private By transactionNumberFromConfirmationLabel = By.xpath("//div[contains(@class,'col-xs-4')]/span[contains(@class,'indent-text')]");
    private By backToTheInboxButton = By.xpath("//button[contains(@class,'redirect-to-tray')]");
    private By sendAndPrintDeliveryStatement = By.xpath("//button[contains(@class,'move-to-assignment')]");
    private By printAssignmentPaperButton = By.xpath("//button[contains(@class,'AssignmentPaper')]");
    private By printAssignmenPaperFromPreview = By.id("printdiv");
    //==================================================================================================================
    //=======================================Assignment_Paper==========================================================
    private By assignmentPaperCta = By.id("assignmentPaper");

    private By imageCheckBoxForAssignmentPaperOrgUnit(String orgUnitName) {
        return By.xpath("(//div[label[text()='"
                + orgUnitName + "']]/following::div[@class='checkbox']/label/span[@class='cr'])[1]");
    }

    private By transferRadioButtonForAssignmentPaperOrgUnit(String orgUnitName) {
        return By.xpath("(//div[label[text()='"
                + orgUnitName + "']]/following::div[@class='radio']/label/span[@class='cr'])[1]");
    }

    private By assignmentPaperAttachmentOverlay = By.cssSelector(".jconfirm.white");
    //First one after the original copy of the transaction
    private By firstAssignmentPaperAttachmentCheckBox = By.xpath("(//input[contains(@class,' inp')])[2]");
    private By saveAttachmentButton = By.id("btnShowCopiesAttDialog");
    private By sendAssignmentButton = By.id("GetUserDelegationsById");
    //================================================================================================================

    @Getter
    private String transactionNumberFromConfirmation;

    @Getter
    private String modifiedTransactionDescription = "Description: " + GeneralOperations.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");

    public InTransactionDraftPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }

    @Step("الحصول على رقم المعاملة من صفحة التعديل")
    public String getInTransactionNumber() {
        return driver.element().getText(incomingTransactionNumber);
    }

    @Step("العودة لصفحة معاملاتى")
    public MyTransactionsPage navigateToMyTransactionsPage() {
        driver.browser().navigateBack();
        return new MyTransactionsPage(driver);
    }

    @Step("الحصول على موضوع المعاملة من صفحة التعديل")
    public String getTransactionSubject() {
        return driver.element().getText(subjectTextField);
    }

    @Step("اضافة ملف على أصل خطاب المعاملة")
    public InTransactionDraftPage addFileToTransactionLetter(String transactionAttachmentLocation) {
        driver.element().switchToIframe(transactionLetterFrame);
        driver.element().click(trasnactionLetterUploadButton).verifyThat(transactionLetterUploadModal).isVisible();
        WebDriver wDriver = driver.getDriver();
        ((JavascriptExecutor) wDriver)
                .executeScript("arguments[0].classList.remove('hidden');", wDriver.findElement(transactionLetterUploadHiddenButton));
        driver.element().typeFileLocationForUpload(transactionLetterUploadHiddenButton, transactionAttachmentLocation);
        driver.element().waitUntilNumberOfElementsToBeMoreThan(transactionLettersThumbnail, 0);
        driver.element().switchToDefaultContent();
        return this;
    }

    @Step("عدد الملحقات")
    public int getNumberOfAttachmentsInGrid() {
        return driver.element().getElementsCount(attachmentsTableRows);
    }

    @Step("تعديل موضوع المعاملة و اعطائه القيمة الحالية للوقت و التاريخ")
    public InTransactionDraftPage modifyInTransactionSubject() {
        driver.element().type(subjectTextField, modifiedTransactionDescription);
        driver.element().type(transactionSubjectTextField, "Transaction Description: " + modifiedTransactionDescription);
        driver.element().click(inTransactionDateCalendar).type(inTransactionDateCalendar, GeneralOperations.getCurrentHijriDate())
                .keyPress(inTransactionDateCalendar, Keys.ESCAPE);
        driver.element().click(inTransactionPriorityLevel)
                .type(inTransactionPriorityLevel, testData.getTestData("incomingPriority")).click(inTransactionPriorityLevel);
        scrollToAndClickSaveButton(saveModifiedTransactionButton);
        return this;
    }

    @Step("حفظ معاملة معدلة")
    public InTransactionDraftPage saveModifiedTransaction() {
        driver.element().scrollToElement(basicInfoTab).click(basicInfoTab);
        driver.element().verifyThat(inDestinationName).isVisible();
        driver.element().type(transactionSubjectTextField, "Transaction Description: " + modifiedTransactionDescription);
        scrollToAndClickSaveButton(saveModifiedTransactionButton);
        return this;
    }

    @Step("العودة لصفحة معاملاتى")
    public MyTransactionsPage goBackToMyTransactionPage() {
        driver.element().click(backToTheInboxButton).verifyThat(backToTheInboxButton).doesNotExist();
        return new MyTransactionsPage(driver);
    }

    @Step("اضافة وارد عام - جهات")
    public InTransactionDraftPage addInGeneralTransactionDestination() {
        driver.element().click(inDataTypeEntities);
        driver.element().click(isLinkCheckBox);
        driver.element().click(inDestinationFilter).verifyThat(inDestinationModal).isVisible();
        driver.element().click(outDestinationEntry)
                .verifyThat(inDestinationName).text().contains(testData.getTestData("incomingDestination"));
        driver.element().type(subjectTextField, modifiedTransactionDescription);
        driver.element().type(inboundDocumentNumber, testData.getTestData("inboundDocumentNumber"));
        driver.element().click(inTransactionDateCalendar).type(inTransactionDateCalendar, GeneralOperations.getCurrentHijriDate())
                .keyPress(inTransactionDateCalendar, Keys.ESCAPE);
        driver.element().click(showNamesButton)
//To be un-commented when needed to send SMS
//                .click(sendSMSCheckBox)
                .type(civilIdNumber, testData.getTestData("civilId"))
                .keyPress(civilIdNumber, Keys.ENTER)
                .verifyThat(firstName).text().isNotNull();
        driver.element().scrollToElement(addNameButton)
                .click(addNameButton).verifyThat(namesGrid).exists();
        return this;
    }

    @Step("اضافة وارد عام - أفراد")
    public InTransactionDraftPage addInGeneralTransactionIndividual() {
        driver.element().click(inDataTypeIndividual);
        driver.element().click(isLinkCheckBox);
        driver.element().click(inDestinationFilter).verifyThat(inDestinationModal).isVisible();
        driver.element().click(outDestinationEntry)
                .verifyThat(inDestinationName).text().contains(testData.getTestData("incomingDestination"));
        driver.element().type(subjectTextField, modifiedTransactionDescription);
        // driver.element().type(inboundDocumentNumber, testData.getTestData("inboundDocumentNumber"));
        driver.element().click(inTransactionDateCalendar).type(inTransactionDateCalendar, GeneralOperations.getCurrentHijriDate())
                .keyPress(inTransactionDateCalendar, Keys.ESCAPE);
        driver.element().click(showNamesButton)
//To be un-commented when needed to send SMS
//                .click(sendSMSCheckBox)
                .type(civilIdNumber, testData.getTestData("civilId"))
                .keyPress(civilIdNumber, Keys.ENTER)
                .verifyThat(firstName).text().isNotNull();
        driver.element().scrollToElement(addNameButton)
                .click(addNameButton).verifyThat(namesGrid).exists();
        return this;
    }

    @Step("حفظ معاملة ")
    public InTransactionDraftPage saveInTransaction() {
        By inputField = driver.element()
                .getElementsCount(saveInTransactionButton) > 0 ? saveInTransactionButton : saveModifiedTransactionButton;
        scrollToAndClickSaveButton(inputField);
        transactionNumberFromConfirmation = driver.element().getText(transactionNumberFromConfirmationLabel);
        return this;
    }

    //Used when modifying a previously added transaction
    @Step("الذهاب الى تبويب الملحقات")
    public InTransactionDraftPage goToAttachmentsTab() {
        driver.element().click(attachmentsTab).verifyThat(attachmentType).isVisible();
        return this;
    }

    @Step("اضافة ملحقات")
    public InTransactionDraftPage addAttachment(String transactionAttachmentType, String transactionAttachmentLocation, String transactionAttachmentVaildity) {
        driver.element().select(attachmentType, transactionAttachmentType);
        //.select(attachmentVailidty, transactionAttachmentVaildity);
        By validityOption = By.xpath("//li[text()='" + transactionAttachmentVaildity + "']");
        driver.element().click(attachmentVailidty).click(validityOption);
        showSelectAttachment();
        driver.element()
                .click(uploadAttachment)
                .typeFileLocationForUpload(chooseAttachmentFile, transactionAttachmentLocation)
                .click(addAttachmentButton)
                .verifyThat(uploadAttachmentContainer).attribute("style").contains("none;");
        return this;
    }

    private void showSelectAttachment() {
        WebDriver wDriver = driver.getDriver();
        ((JavascriptExecutor) wDriver)
                .executeScript("arguments[0].classList.remove('hidden');", wDriver.findElement(chooseAttachmentFile));
    }


    @Step("الذهاب الى تبويب النسخ الداخلية الالكترونية")
    private InTransactionDraftPage goToTransactionCopiesTab() {
        driver.element().click(transactionCopiesTab).verifyThat(orgnaisationalUnitNumber).isVisible();
        return this;
    }

    @Step("اضافة نسخ داخلية")
    public InTransactionDraftPage addInternalCopies(String orgUnitNum, String copyReason, int attachmentIndex) {
        goToTransactionCopiesTab();
        int numOfCopies = getNumberOfCopyRows();
        By attachmentCheckBoxIndexElement = By.xpath(String.format(attachmentCheckBoxAtIndex, attachmentIndex + 1));
        By attachmentTypeAtIndex = By.xpath(String.format(attachmentCopyTransactionDescriptionAtIndex, attachmentIndex + 1));
        attachmentCopyDescription.add(driver.element().getText(attachmentTypeAtIndex));
        driver.element().click(orgnaisationalUnitNumber).type(orgnaisationalUnitNumber, orgUnitNum)
                .waitToBeReady(orgUnitAutoCompleteMenu).click(firstOrgChartAutoSuggestion);
        driver.element().select(copyActionReason, copyReason);
        driver.element().click(attachmentCheckBoxIndexElement).click(addCopyButton)
                .waitUntilNumberOfElementsToBeMoreThan(copyRowInGrid, numOfCopies);
        return this;
    }

    @Step("عدد النسخ")
    public int getNumberOfCopyRows() {
        return driver.element().getElementsCount(copyRowInGrid);
    }

    @Step("حفظ المعاملة")
    private void scrollToAndClickSaveButton(By byButtonLocator) {
        driver.element().scrollToElement(byButtonLocator).click(byButtonLocator);
        driver.element().verifyThat(confirmationAndSuccessModal).isVisible();
        driver.element().click(confirmationAgreeButton).verifyThat(confirmationAndSuccessModal).isVisible().perform();
    }

    @Step("طباعة بيان التسليم اثناء حفظ المعاملة المعدلة")
    public MyTransactionsPage sendAndPrintDeliveryStatementForModifiedInTransaction() {
        driver.element().type(transactionSubjectTextField, "Description: " + GeneralOperations.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
        driver.element().scrollToElement(saveModifiedTransactionButton);
        //driver.element().clickUsingJavascript(saveModifiedTransactionButton);
        // driver.element().click(saveModifiedTransactionButton);
        WebElement element = driver.getDriver().findElement(saveModifiedTransactionButton);
        // Use JavascriptExecutor to click the element
        JavascriptExecutor js = (JavascriptExecutor) driver.getDriver();
        js.executeScript("arguments[0].click();", element);
        driver.element().verifyThat(confirmationAndSuccessModal).isVisible();
        driver.element().click(confirmationAgreeButton).verifyThat(confirmationAndSuccessModal).isVisible().perform();
        driver.element().click(sendAndPrintDeliveryStatement).browser().waitUntilNumberOfWindowsToBe(2).waitUntilNumberOfWindowsToBe(1);
        return new MyTransactionsPage(driver);
    }

    @Step("طباعة بيان التسليم اثناء حفظ المعاملة المضافة")
    public MyTransactionsPage printDeliveryStatementForAddedInTransaction() {
        driver.element().click(sendAndPrintDeliveryStatement).browser().waitUntilNumberOfWindowsToBe(2).waitUntilNumberOfWindowsToBe(1);
        return new MyTransactionsPage(driver);
    }

    //Used when adding a new transaction
    @Step("توسيع قسم الملحقات")
    public InTransactionDraftPage expandAttachmentSection() {
        driver.element().scrollToElement(baseTransactionAttachmentsButton)
                .click(baseTransactionAttachmentsButton).verifyThat(attachmentType).isVisible();
        return this;
    }

    @Step("طباعة ورقة الاحالة")
    public InTransactionDraftPage printAssignmentPaperFromConfirmation() {
        driver.element().click(printAssignmentPaperButton).verifyThat(printAssignmenPaperFromPreview).isVisible();
        driver.element().clickUsingJavascript(printAssignmenPaperFromPreview).verifyThat(loadingSpinner).isVisible();
        return this;
    }

    @Step("الذهاب الى تبويب المتابعة")
    public InTransactionDraftPage goToFollowupTab() {
        driver.element().click(followupTab).waitToBeReady(followupOrgUnitNumber);
        return this;
    }

    @Step("الحصول على عدد طلبات المتابعة")
    public int getNumberOfFollowupRequests() {
        return driver.element().getElementsCount(transactionFollowupGridRow);
    }

    @Step("اضافة متابعة الى المعاملة")
    public InTransactionDraftPage addTransactionFollowup(String orgUnitNumber) {
        goToFollowupTab();
        int numberOfFollowupRequests = getNumberOfFollowupRequests();
        driver.element().click(followupOrgUnitNumber).clear(followupOrgUnitNumber).type(followupOrgUnitNumber, orgUnitNumber)
                .waitToBeReady(followupOrgUnitAutoCompleteMenu).click(followupFirstOrgChartAutoSuggestion);
        driver.element().click(addTransactionFollowupButton)
                .waitToBeReady(followupConfirmationAndSuccessModal)
                .click(confirmAddFollowup)
                .waitUntilNumberOfElementsToBeMoreThan(transactionFollowupGridRow, numberOfFollowupRequests);
        return this;
    }

    @Step("الذهاب الى ورقة الاحالة")
    public InTransactionDraftPage goToAssignmentPaper() {
        driver.element().type(transactionSubjectTextField, "Transaction Description: " + modifiedTransactionDescription);
        driver.element().click(assignmentPaperCta);
        return this;
    }

    @Step("اختيار ادارة للارسال من خلال ورقة الاحالة")
    public InTransactionDraftPage selectOrgUnitFromAssignmentPaper(String orgUnitName) {
        driver.element().click(transferRadioButtonForAssignmentPaperOrgUnit(orgUnitName))
                .click(imageCheckBoxForAssignmentPaperOrgUnit(orgUnitName))
                .waitUntilNumberOfElementsToBe(assignmentPaperAttachmentOverlay, 1);
        driver.element().click(firstAssignmentPaperAttachmentCheckBox).click(saveAttachmentButton)
                .verifyThat(assignmentPaperAttachmentOverlay).doesNotExist();
        return this;
    }

    @Step("ارسال ورقة الاحالة")
    public MyTransactionsPage sendAssignmentPaper() {
        driver.element().scrollToElement(sendAssignmentButton)
                .click(sendAssignmentButton)
                .verifyThat(sendAssignmentButton);
        return new MyTransactionsPage(driver);
    }

}