package tansactions.وارد_عام;

import base.TestBase;
import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import io.qameta.allure.Description;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.transactions.InTransactionDraftPage;
import pages.LoginPage;
import pages.transactions.MyTransactionsPage;

public class ModifyGeneralIncomingTransactionTest extends TestBase {

    @BeforeMethod
    public void beforeTest() {
        testData = new SHAFT.TestData.JSON("appData.json");
        openBuragApp();
    }

    @AfterMethod
    public void afterTest() {
        driver.quit();
    }

    //=============================
    @Test(description = "[1.3] تعديل وارد جهات")
    @Description("[1.3] تعديل وارد جهات")
    public void modifyIncomingTransaction() {
        LoginPage loginPage = new LoginPage(driver);
        MyTransactionsPage myTransactionsPage = loginPage.loginToTheApp();
        InTransactionDraftPage inTransactionDraftPage = myTransactionsPage.searchForGeneralInTransaction()
                .modifyFirstGeneralIncomingTransactionDestination().modifyInTransactionSubject();

        String transactionNumber = inTransactionDraftPage.getInTransactionNumber();
        String transactionSubject = inTransactionDraftPage.getModifiedTransactionDescription();

        myTransactionsPage = inTransactionDraftPage.goBackToMyTransactionPage()
                .getTransactionsOperationsComponent().searchForTransactionWithId(transactionNumber, new MyTransactionsPage(driver));

        String transactionNumberOnCard = myTransactionsPage.getFirstTransactionNumber();
        String transactionSubjectOnCard = myTransactionsPage.getFirstTransactionDescription();

        Validations.assertThat().object(transactionNumberOnCard).equals(transactionNumber);
        Validations.assertThat().object(transactionSubjectOnCard).equals(transactionSubject);
    }

}
