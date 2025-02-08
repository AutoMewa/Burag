package reports;

import base.TestBase;
import com.shaft.driver.SHAFT;
import com.shaft.validation.Validations;
import io.qameta.allure.Description;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.reports.TransactionsGeneralReportPage;
import pages.reports.TransactionsReportPage;
import pages.transactions.MyTransactionsPage;
import utils.GeneralOperations;

@Slf4j
public class TransactionGeneralReportsTest extends TestBase {



    @BeforeMethod(alwaysRun = true)
    public void beforeTest() {
        testData = new SHAFT.TestData.JSON("appData.json");
        openBuragApp();
    }

    @AfterMethod(alwaysRun = true)
    public void afterTest() {
        driver.quit();
    }

    //=============================
    //@Test(description = "[7.1.8] تقرير المعاملات الشامل", groups = {"Smoke"})
    @Description("[7.1.8] تقرير المعاملات الشامل")
    public void generateTransactionsGeneralReport() {
        SHAFT.TestData.JSON reportData = new SHAFT.TestData.JSON("transactionsReports.json");
        LoginPage loginPage = new LoginPage(driver);
        MyTransactionsPage myTransactionsPage = loginPage.loginToTheApp();
        TransactionsGeneralReportPage transactionsGeneralReportPage = myTransactionsPage.getHMComponent()
                .navigateToReportsTab().navigateToTransactionsGeneralReport();
        String fromDate = GeneralOperations.getHijriDateWeeksBefore(1);
        String toDate = GeneralOperations.getCurrentHijriDate();

        transactionsGeneralReportPage.generateReportForTransactionType(fromDate, toDate, reportData.getTestData("transactionReport1.type"));
        Validations.verifyThat().object(transactionsGeneralReportPage.isResultsGridDisplayed()).isTrue();
        Validations.verifyThat().number(transactionsGeneralReportPage.getRowsCount()).isGreaterThanOrEquals(0);
        Validations.verifyThat().object(transactionsGeneralReportPage.getDateRangeInReport()).contains(fromDate+" - "+toDate);
        Validations.verifyThat().object(transactionsGeneralReportPage.getTransactionTypeInReport())
                .contains(reportData.getTestData("transactionReport1.type"));

/*        transactionsGeneralReportPage.generateReportForTransactionType(fromDate, toDate, reportData.getTestData("transactionReport2.type"));
        Validations.verifyThat().object(transactionsGeneralReportPage.isResultsGridDisplayed()).isTrue();
        Validations.verifyThat().number(transactionsGeneralReportPage.getRowsCount()).isGreaterThanOrEquals(0);
        Validations.verifyThat().object(transactionsGeneralReportPage.getDateRangeInReport()).contains(fromDate+" - "+toDate);
        Validations.verifyThat().object(transactionsGeneralReportPage.getTransactionTypeInReport())
                .contains(reportData.getTestData("transactionReport2.type"));

        transactionsGeneralReportPage.generateReportForTransactionType(fromDate, toDate, reportData.getTestData("transactionReport3.type"));
        Validations.verifyThat().object(transactionsGeneralReportPage.isResultsGridDisplayed()).isTrue();
        Validations.verifyThat().number(transactionsGeneralReportPage.getRowsCount()).isGreaterThanOrEquals(0);
        Validations.verifyThat().object(transactionsGeneralReportPage.getDateRangeInReport()).contains(fromDate+" - "+toDate);
        Validations.verifyThat().object(transactionsGeneralReportPage.getTransactionTypeInReport())
                .contains(reportData.getTestData("transactionReport3.type"));*/

    }
}