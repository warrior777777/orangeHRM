package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.ClaimPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class Claim_Advanced_Test extends BaseTest {

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

    @Test(priority = 1, description = "TC01 - Verify Admin Assigning Claim to Employee")
    public void verifyAssignClaim_Success() {
        String targetEmployee = "Script Automation Tester";
        claimPage
                .navigateToSection("Assign Claim")
                .typeInDynamicField("Employee Name", targetEmployee)
                .selectFromList()
                .clickAndSelectDropdown("Event", "Global QA Summit 2025")
                .selectCurrency("Euro")
                .typeInDynamicTextArea("Remarks", "Travel Reimbursement")
                .clickToSubmit()
                .verifySuccessMessage();
        claimPage
                .clickAddExpense()
                .clickAndSelectDropdown("Expense Type", "Transport")
                .selectDate("Date", "2024-7-01")
                .typeInDynamicField("Amount", "320.00")
                .clickSave()
                .verifySuccessMessage();
        claimPage
                .clickAddExpense()
                .clickAndSelectDropdown("Expense Type", "Team Lunch")
                .selectDate("Date", "2024-7-01")
                .typeInDynamicField("Amount", "70.00")
                .clickSave()
                .verifySuccessMessage();

    }

    @Test(priority = 2, description = "TC02 - Verify My Claims History Search")
    public void verifyMyClaims_History() {
        String fromDate = "2024-01-01";
        String toDate = "2024-12-31";

        claimPage
                .navigateToSection("My Claims")
                .selectDate("From Date", fromDate)
                .selectDate("To Date", toDate)
                .searchUser()
                .verifySearchTable();
    }
}