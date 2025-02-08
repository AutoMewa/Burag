import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.transactions.OutTransactionsPage;
import pages.transactions.OutTransactionDraftPage;
import pages.transactions.MyTransactionsPage;
import base.TestBase;
import pages.transactions.TransactionCopiesDetailsPage;

@Slf4j
public class TrialTestClass extends TestBase {

    @BeforeTest
    public void beforeTest() {
        testData = new SHAFT.TestData.JSON("appData.json");
        openBuragApp();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    //=============================
    @Test(description="تعديل صادر عام [3.2]")
    @Description("تعديل صادر عام [3.2]")
    public void modifyIncomingTransaction() {
        LoginPage loginPage = new LoginPage(driver);
        MyTransactionsPage myTransactionsPage = loginPage.loginToTheApp();
        TransactionCopiesDetailsPage transactionCopiesDetailsPage= myTransactionsPage.navigateToTransactionsCopies()
                .goToTransactionCopyDetails();
        myTransactionsPage = transactionCopiesDetailsPage.navigateToMyTransactions()
                .getSystemAdminComponent().changeDepartment("مكتب الوزير");
        driver.browser().captureScreenshot();
        transactionCopiesDetailsPage= myTransactionsPage.navigateToTransactionsCopies()
                .goToTransactionCopyDetails();
        myTransactionsPage = transactionCopiesDetailsPage.navigateToMyTransactions()
                .getSystemAdminComponent().changeDepartment("وكالة البيئة");
        driver.browser().captureScreenshot();
        transactionCopiesDetailsPage= myTransactionsPage.navigateToTransactionsCopies()
                .goToTransactionCopyDetails();
        myTransactionsPage = transactionCopiesDetailsPage.navigateToMyTransactions()
                .getSystemAdminComponent().changeDepartment("مكتب نائب الوزير");
        driver.browser().captureScreenshot();
    }
}
