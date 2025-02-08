package pages.transactions;

import com.shaft.driver.SHAFT;
import com.shaft.gui.internal.locator.Locator;
import components.SystemAdminComponent;
import components.TransactionsOperationsComponent;
import components.HorizontalMenusComponent;
import components.TransactionsNavigationPanelComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;

public class MyTransactionsPage extends TransactionsNavigationPanelComponent {


    private By appLogo = By.className("main_logo");
    private By myTransactions = By.id("MyTransactions");
    private By mainLtitle = By.xpath("//h3[contains(text(), 'معاملاتي')]");
    private By firstEditTransactionButotn = Locator.hasTagName("i").containsClass("icon-outline-edit").isFirst().build();
    private By firstTransactionNumber = Locator.hasTagName("div").containsClass("pr-1").isFirst().build();
    private By firstTransactionDescription = Locator.hasTagName("div").containsClass("description").isFirst().build();
    private By filterButton = By.className("icon-filter");
    private By filterTransactionType = By.id("TransactionType");
    private By filterInTransactionType = By.xpath("//li[@data-value='254']");
    private By filterSearchButton = By.xpath("//input[@type='button']");
    private By firstGeneralInTransactionDestination = By.xpath("(//div[contains(text(), ' وارد خارجي (جهات)')])[1]");
    private By firstEditInDestination = Locator.hasTagName("i").containsClass("icon-outline-edit")
            .relativeBy().below(firstGeneralInTransactionDestination);
    private By firstAttachmentsNumInDestination = Locator.hasAnyTagName().containsClass("span")
            .isFirst().relativeBy().below(firstGeneralInTransactionDestination);
    private By numberOfAttachmentsAtFirstTransaction = By.xpath("(//div[contains(@class,'ml-auto')]/div[@class='info']/span)[1]");
    private By firstTransactionCheckbox = By.xpath("(//div[contains(@class,'ml-auto')]/div[@class='checkbox'])[1]");
    private By multiOptionsSideBar = By.cssSelector(".sidebarMultiOptions.transparent-bg");
    private By sideBarReferral = By.xpath("(//div[contains(@class,'sidebarMultiOptions')]//a[contains(@class,'boardered-circle-transparent')])[1]");

    @Getter
    private TransactionsOperationsComponent transactionsOperationsComponent;
    @Getter
    private SystemAdminComponent systemAdminComponent;

    public MyTransactionsPage(SHAFT.GUI.WebDriver driver) {
        super(driver, new HorizontalMenusComponent(driver));
        transactionsOperationsComponent = new TransactionsOperationsComponent(driver);
        systemAdminComponent = new SystemAdminComponent(driver);
    }

    @Step("تعديل المعاملة الواردة الأولى / الذهاب الى صفحة \"تعديل وارد\"")
    public InTransactionDraftPage editFirstInTransaction() {
        driver.element().click(firstEditTransactionButotn);
        return new InTransactionDraftPage(driver);
    }

    @Step("اختيار أول معاملة")
    public MyTransactionsPage selectFirstTransaction() {
        driver.element().click(firstTransactionCheckbox)
                .waitUntilNumberOfElementsToBe(multiOptionsSideBar, 1);
        return this;
    }


    /**
     * ToDo
     * This method needs to be modified, generalized & moved to the "TransactionsOperationsComponent.java" class
     * to be usable for all transaction pages instead of adding a separate method for each of the transaction pages
     *
     * @return
     */
    @Step("البحث عن وارد عام")
    public MyTransactionsPage searchForGeneralInTransaction() {
        driver.element().click(filterButton);
        driver.element().click(filterTransactionType).click(filterInTransactionType);
        driver.element().click(filterSearchButton).verifyThat(filterSearchButton).isEnabled();
        return this;
    }

    @Step("لبذهاب الى صفحة تعديل وارد عام(جهات)")
    public InTransactionDraftPage modifyFirstGeneralIncomingTransactionDestination() {
        driver.element().scrollToElement(firstGeneralInTransactionDestination);
        driver.element().click(firstEditInDestination);
        return new InTransactionDraftPage(driver);
    }

    @Step("عدد الملحقات فى المعاملة الأولى وارد عام(جهات)")
    public int getFirstTrxDestinationAttachmentsNumber() {
        driver.element().scrollToElement(firstGeneralInTransactionDestination);
        String trimmedNumber = driver.element().getText(firstAttachmentsNumInDestination).replaceAll("[^0-9]", "");
        return Integer.parseInt(trimmedNumber);
    }

    @Step("الحصول على رقم المعاملة الأولى")
    public String getFirstTransactionNumber() {
        return driver.element().getText(firstTransactionNumber);
    }

    @Step("الحصول على موضوع/وصف المعاملة الأولى")
    public String getFirstTransactionDescription() {
        return driver.element().getText(firstTransactionDescription);
    }

    @Step("الحصول على عدد ملحقات المعاملة الأولى")
    public int getNumberOfAttachmentsOnFirstCard() {
        driver.element().scrollToElement(numberOfAttachmentsAtFirstTransaction);
        String trimmedNumber = driver.element().getText(numberOfAttachmentsAtFirstTransaction).replaceAll("[^0-9]", "");
        return Integer.parseInt(trimmedNumber);
    }

    @Step("الذهاب الى صفحة الاحالة المباشرة")
    public DirectReferralPage navigateToDirectReferral() {
        driver.element().click(sideBarReferral)
                .verifyThat(multiOptionsSideBar)
                .doesNotExist();
        return new DirectReferralPage(driver);
    }

}
