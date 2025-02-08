package components;

import com.shaft.driver.SHAFT;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import pages.transactions.*;

public class TransactionsNavigationPanelComponent{

    protected SHAFT.GUI.WebDriver driver;
    @Getter
    protected HorizontalMenusComponent hMComponent;

    private By outTransactions = By.xpath("//a[contains(@href,'DraftOutbound')]");
    private By myTransactions = By.xpath("//div[contains(@class,'main-menu-content')]//a[@href='/MCS.UI.Tenants/User']");
    private By briefcase = By.xpath("//a[contains(@href,'/DisplayBriefcase')]");
    private By transactionCopies = By.xpath("//a[contains(@href,'/Copies')]");
    private By transactionFollowup =  By.xpath("//a[@href='/MCS.UI.Tenants/User/File/FollowUp']");


    public TransactionsNavigationPanelComponent(SHAFT.GUI.WebDriver driver, HorizontalMenusComponent hMComponent) {
        this.driver = driver;
        this.hMComponent = hMComponent;
    }

    @Step("الذهاب الى شاشة \"المعاملات الصادرة\"")
    public OutTransactionsPage navigateToOutTransactions() {
        driver.element().click(outTransactions);
        return new OutTransactionsPage(driver);
    }

    @Step("الذهاب الى شاشة \"معاملاتى\"")
    public MyTransactionsPage navigateToMyTransactions() {
        driver.element().click(myTransactions);
        return new MyTransactionsPage(driver);
    }


    @Step("الذهاب الى شاشة \"حقيبة العرض\"")
    public BriefcasePage navigateToBriefcase() {
        driver.element().click(briefcase);
        return new BriefcasePage(driver);
    }

    @Step("الذهاب الى شاشة \"صور المعاملات\"")
    public TransactionsCopiesPage navigateToTransactionsCopies() {
        driver.element().waitUntilNumberOfElementsToBe(transactionCopies, 1).click(transactionCopies);
        return new TransactionsCopiesPage(driver);
    }

    @Step("الذهاب الى شاشة \"المتابعة\"")
    public TransactionsFollowupPage navigateToTransactionFollowup() {
        driver.element().waitUntilNumberOfElementsToBe(transactionFollowup, 1).click(transactionFollowup);
        return new TransactionsFollowupPage(driver);
    }
}
