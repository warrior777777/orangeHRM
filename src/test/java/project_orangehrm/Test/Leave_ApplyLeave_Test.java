package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LeavePage;
import project_orangehrm.Pages.LoginPage;

public class Leave_ApplyLeave_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private LeavePage leavePage;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        leavePage = new LeavePage(driver);

        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .verifyDashboard("Dashboard");
        dashboardPage
                .navigateToModule("Leave");
    }

    @Test(priority = 1, description = "TC01 - Verify Apply Leave Success")
    public void verifyApplyLeave_Success() {
        String leaveType = "US - Vacation";
        String fromDate = "2025-01-15";
        String toDate = "2025-01-17";

        leavePage
                .navigateToSection("Apply")
                .clickAndSelectDropdown("Leave Type", leaveType)
                .selectDate("From Date", fromDate)
                .selectDate("To Date", toDate)
                .typeInDynamicTextArea("Comments", "Vacation - Automation Test")
                .clickToSubmit()
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "TC02 - Verify Apply Leave Validation - Empty Fields")
    public void verifyApplyLeave_EmptyFields_ShowError() {
        leavePage
                .navigateToSection("Apply")
                .clickToSubmit()
                .verifyFieldErrorMessage("Leave Type", "Required")
                .verifyFieldErrorMessage("From Date", "Required")
                .verifyFieldErrorMessage("To Date", "Required");
    }

    @Test(priority = 3, description = "TC03 - Verify Apply Leave with Half Day")
    public void verifyApplyLeave_HalfDay_Success() {
        String leaveType = "US - Vacation";
        String date = "2025-01-20";

        leavePage
                .navigateToSection("Apply")
                .clickAndSelectDropdown("Leave Type", leaveType)
                .selectDate("From Date", date)
                .selectDate("To Date", date)
                .clickAndSelectDropdown("Duration", "Half Day - Morning")
                .typeInDynamicTextArea("Comments", "Half day leave - Auto Test")
                .clickToSubmit()
                .verifySuccessMessage();
    }

    @Test(priority = 4, description = "TC04 - Verify Apply Leave Balance Check")
    public void verifyApplyLeave_BalanceDisplay() {
        String leaveType = "US - Vacation";

        leavePage
                .navigateToSection("Apply")
                .clickAndSelectDropdown("Leave Type", leaveType)
                .verifyElementVisible("Balance");
    }


    @Test(priority = 5, description = "TC05 - Verify My Leave List Display")
    public void verifyMyLeave_ListDisplay_Success() {
        leavePage
                .navigateToSection("My Leave")
                .verifySearchTable();
    }

    @Test(priority = 6, description = "TC06 - Verify My Leave Filter by Status")
    public void verifyMyLeave_FilterByStatus_Success() {
        leavePage
                .navigateToSection("My Leave")
                .clickAndSelectDropdown("Show Leave with Status", "Pending Approval")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 7, description = "TC07 - Verify My Leave Filter by Date Range")
    public void verifyMyLeave_FilterByDateRange_Success() {
        String fromDate = "2024-01-01";
        String toDate = "2025-12-31";

        leavePage
                .navigateToSection("My Leave")
                .selectDate("From Date", fromDate)
                .selectDate("To Date", toDate)
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 8, description = "TC08 - Verify My Leave Cancel Pending Request")
    public void verifyMyLeave_CancelRequest_Success() {
        leavePage
                .navigateToSection("My Leave")
                .clickAndSelectDropdown("Show Leave with Status", "Pending Approval")
                .searchUser()
                .clickCancelLeave()
                .verifySuccessMessage();
    }

    @Test(priority = 9, description = "TC09 - Verify My Leave Reset Filters")
    public void verifyMyLeave_ResetFilters_Success() {
        leavePage
                .navigateToSection("My Leave")
                .selectDate("From Date", "2024-01-01")
                .selectDate("To Date", "2024-12-31")
                .clickAndSelectDropdown("Show Leave with Status", "Scheduled")
                .clickToReset()
                .verifySearchTable();
    }

    @Test(priority = 10, description = "TC10 - Verify My Entitlements Display")
    public void verifyMyEntitlements_Display_Success() {
        leavePage
                .navigateToSection("Entitlements", "My Entitlements")
                .clickAndSelectDropdown("Leave Type", "US - Vacation")
                .searchUser()
                .verifySearchTable();
    }
}

