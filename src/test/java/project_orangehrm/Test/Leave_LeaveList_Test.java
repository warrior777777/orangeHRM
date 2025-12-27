package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LeavePage;
import project_orangehrm.Pages.LoginPage;

public class Leave_LeaveList_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private LeavePage leavePage;

    @BeforeMethod(alwaysRun = true)
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

    @Test(priority = 1, description = "TC01 - Verify Leave List Display")
    public void verifyLeaveList_Display_Success() {
        leavePage
                .navigateToSection("Leave List")
                .verifySearchTable();
    }

    @Test(priority = 2, description = "TC02 - Verify Leave List Filter by Date")
    public void verifyLeaveList_FilterByDate_Success() {
        String fromDate = "2024-01-01";
        String toDate = "2025-12-31";

        leavePage
                .navigateToSection("Leave List")
                .selectDate("From Date", fromDate)
                .selectDate("To Date", toDate)
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 3, description = "TC03 - Verify Leave List Filter by Status")
    public void verifyLeaveList_FilterByStatus_Success() {
        leavePage
                .navigateToSection("Leave List")
                .clickAndSelectDropdown("Show Leave with Status", "Pending Approval")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 4, description = "TC04 - Verify Leave List Filter by Employee")
    public void verifyLeaveList_FilterByEmployee_Success() {
        String employeeName = "Script Automation Tester";

        leavePage
                .navigateToSection("Leave List")
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 5, description = "TC05 - Verify Leave List Filter by Leave Type")
    public void verifyLeaveList_FilterByLeaveType_Success() {
        leavePage
                .navigateToSection("Leave List")
                .clickAndSelectDropdown("Leave Type", "US - Vacation")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 6, description = "TC06 - Verify Leave List Filter by Sub Unit")
    public void verifyLeaveList_FilterBySubUnit_Success() {
        leavePage
                .navigateToSection("Leave List")
                .clickAndSelectDropdown("Subunit", "Engineering")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 7, description = "TC07 - Verify Leave Approve Action")
    public void verifyLeaveList_Approve_Success() {
        leavePage
                .navigateToSection("Leave List")
                .clickAndSelectDropdown("Show Leave with Status", "Pending Approval")
                .searchUser()
                .clickApproveLeave()
                .verifySuccessMessage();
    }

    @Test(priority = 8, description = "TC08 - Verify Leave Reject Action")
    public void verifyLeaveList_Reject_Success() {
        leavePage
                .navigateToSection("Leave List")
                .clickAndSelectDropdown("Show Leave with Status", "Pending Approval")
                .searchUser()
                .clickRejectLeave()
                .verifySuccessMessage();
    }

    @Test(priority = 9, description = "TC09 - Verify Leave Cancel Action")
    public void verifyLeaveList_Cancel_Success() {
        leavePage
                .navigateToSection("Leave List")
                .clickAndSelectDropdown("Show Leave with Status", "Scheduled")
                .searchUser()
                .clickCancelLeave()
                .verifySuccessMessage();
    }

    @Test(priority = 10, description = "TC10 - Verify Leave List View Details")
    public void verifyLeaveList_ViewDetails_Success() {
        leavePage
                .navigateToSection("Leave List")
                .searchUser()
                .clickViewDetails()
                .verifyElementVisible("Leave Details");
    }

    @Test(priority = 11, description = "TC11 - Verify Leave List Add Comment")
    public void verifyLeaveList_AddComment_Success() {
        leavePage
                .navigateToSection("Leave List")
                .clickAndSelectDropdown("Show Leave with Status", "Pending Approval")
                .searchUser()
                .clickAddComment()
                .typeInDynamicTextArea("Comment", "Approved by Admin - Auto Test")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 12, description = "TC12 - Verify Leave List Reset Filters")
    public void verifyLeaveList_ResetFilters_Success() {
        leavePage
                .navigateToSection("Leave List")
                .selectDate("From Date", "2024-01-01")
                .selectDate("To Date", "2024-12-31")
                .clickAndSelectDropdown("Show Leave with Status", "Scheduled")
                .clickToReset()
                .verifySearchTable();
    }
}

