package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.ClaimPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class Claim_Operations_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ClaimPage claimPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        claimPage = new ClaimPage(driver);

        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .navigateToModule("Claim");
    }


    @Test(priority = 1, description = "TC01 - Verify Submit Claim Logic")
    public void verifySubmitClaim_Logic() {

        claimPage
                .navigateToSection("Submit Claim")
                .clickAndSelectDropdown("Event", "Global QA Summit 2025")
                .selectCurrency("United States Dollar")
                .clickToSubmit()
                .verifySuccessMessage();
        claimPage
                .clickAddExpense()
                .clickAndSelectDropdown("Expense Type", "Transport")
                .selectDate("Date", "2024-12-01")
                .typeInDynamicField("Amount", "50.00")
                .clickSave()
                .verifySuccessMessage();
        claimPage
                .clickAddExpense()
                .clickAndSelectDropdown("Expense Type", "Accommodation")
                .selectDate("Date", "2024-12-01")
                .typeInDynamicField("Amount", "20")
                .clickSave()
                .clickToSubmit();
    }


    @Test(priority = 2, description = "TC02 - Verify Submit, Edit Claim Logic")
    public void verifyClaim_Edit() {

        claimPage
                .navigateToSection("Submit Claim")
                .clickAndSelectDropdown("Event", "Global QA Summit 2025")
                .selectCurrency("United States Dollar")
                .clickToSubmit()
                .verifySuccessMessage();
        claimPage
                .clickAddExpense()
                .clickAndSelectDropdown("Expense Type", "Transport")
                .selectDate("Date", "2024-12-01")
                .typeInDynamicField("Amount", "50.00")
                .clickSave()
                .verifySuccessMessage();
        claimPage
                .clickToEdit("Transport")
                .typeInDynamicField("Amount", "90");
        claimPage
                .clickAddExpense()
                .clickAndSelectDropdown("Expense Type", "Accommodation")
                .selectDate("Date", "2024-12-01")
                .typeInDynamicField("Amount", "20")
                .clickSave();
        claimPage
                .clickToEdit("Accommodation")
                .typeInDynamicField("Amount", "100");
        claimPage
                .clickToSubmit()
                .verifySuccessMessage();
    }

}