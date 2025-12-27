package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.ClaimPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class Claim_EmployeeClaims_Test extends BaseTest {

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

    @Test(priority = 1, description = "TC01 - Verify Employee Claims Page Display")
    public void verifyEmployeeClaims_Display_Success() {
        claimPage
                .navigateToSection("Employee Claims")
                .verifySearchTable();
    }

    @Test(priority = 2, description = "TC02 - Verify Employee Claims Search by Employee")
    public void verifyEmployeeClaims_SearchByEmployee_Success() {
        String employeeName = "Script Automation Tester";

        claimPage
                .navigateToSection("Employee Claims")
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 3, description = "TC03 - Verify Employee Claims Search by Reference ID")
    public void verifyEmployeeClaims_SearchByReferenceId_Success() {
        claimPage
                .navigateToSection("Employee Claims")
                .typeInDynamicField("Reference Id", "CLM")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 4, description = "TC04 - Verify Employee Claims Search by Event")
    public void verifyEmployeeClaims_SearchByEvent_Success() {
        claimPage
                .navigateToSection("Employee Claims")
                .clickAndSelectDropdown("Event Name", "Global QA Summit 2025")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 5, description = "TC05 - Verify Employee Claims Search by Status")
    public void verifyEmployeeClaims_SearchByStatus_Success() {
        claimPage
                .navigateToSection("Employee Claims")
                .clickAndSelectDropdown("Status", "Submitted")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 6, description = "TC06 - Verify Employee Claims Filter by Date Range")
    public void verifyEmployeeClaims_FilterByDateRange_Success() {
        String fromDate = "2024-01-01";
        String toDate = "2025-12-31";

        claimPage
                .navigateToSection("Employee Claims")
                .selectDate("From Date", fromDate)
                .selectDate("To Date", toDate)
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 7, description = "TC07 - Verify Employee Claims Approve Action")
    public void verifyEmployeeClaims_Approve_Success() {
        claimPage
                .navigateToSection("Employee Claims")
                .clickAndSelectDropdown("Status", "Submitted")
                .searchUser()
                .clickViewClaim()
                .clickApproveClaim()
                .verifySuccessMessage();
    }

    @Test(priority = 8, description = "TC08 - Verify Employee Claims Reject Action")
    public void verifyEmployeeClaims_Reject_Success() {
        claimPage
                .navigateToSection("Employee Claims")
                .clickAndSelectDropdown("Status", "Submitted")
                .searchUser()
                .clickViewClaim()
                .clickRejectClaim()
                .verifySuccessMessage();
    }

    @Test(priority = 9, description = "TC09 - Verify Employee Claims View Details")
    public void verifyEmployeeClaims_ViewDetails_Success() {
        claimPage
                .navigateToSection("Employee Claims")
                .searchUser()
                .clickViewClaim()
                .verifyElementVisible("Claim Details");
    }

    @Test(priority = 10, description = "TC10 - Verify Employee Claims Pay Action")
    public void verifyEmployeeClaims_Pay_Success() {
        claimPage
                .navigateToSection("Employee Claims")
                .clickAndSelectDropdown("Status", "Approved")
                .searchUser()
                .clickViewClaim()
                .clickPayClaim()
                .verifySuccessMessage();
    }

    @Test(priority = 11, description = "TC11 - Verify Employee Claims Cancel Action")
    public void verifyEmployeeClaims_Cancel_Success() {
        claimPage
                .navigateToSection("Employee Claims")
                .clickAndSelectDropdown("Status", "Submitted")
                .searchUser()
                .clickViewClaim()
                .clickCancelClaim()
                .verifySuccessMessage();
    }

    @Test(priority = 12, description = "TC12 - Verify Employee Claims Reset Filters")
    public void verifyEmployeeClaims_ResetFilters_Success() {
        claimPage
                .navigateToSection("Employee Claims")
                .clickAndSelectDropdown("Status", "Submitted")
                .selectDate("From Date", "2024-01-01")
                .clickToReset()
                .verifySearchTable();
    }

    @Test(priority = 13, description = "TC13 - Verify My Claims View Details")
    public void verifyMyClaims_ViewDetails_Success() {
        claimPage
                .navigateToSection("My Claims")
                .searchUser()
                .clickViewClaim()
                .verifyElementVisible("Claim Details");
    }

    @Test(priority = 14, description = "TC14 - Verify My Claims Filter by Event")
    public void verifyMyClaims_FilterByEvent_Success() {
        claimPage
                .navigateToSection("My Claims")
                .clickAndSelectDropdown("Event Name", "Global QA Summit 2025")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 15, description = "TC15 - Verify My Claims Filter by Status")
    public void verifyMyClaims_FilterByStatus_Success() {
        claimPage
                .navigateToSection("My Claims")
                .clickAndSelectDropdown("Status", "Submitted")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 16, description = "TC16 - Verify My Claims Reset Filters")
    public void verifyMyClaims_ResetFilters_Success() {
        claimPage
                .navigateToSection("My Claims")
                .clickAndSelectDropdown("Status", "Submitted")
                .selectDate("From Date", "2024-01-01")
                .clickToReset()
                .verifySearchTable();
    }
}

