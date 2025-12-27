package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LeavePage;
import project_orangehrm.Pages.LoginPage;

public class Leave_Operations_Test extends BaseTest {

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


    @Test(priority = 1, description = "TC01 - Assign Leave to Employee")
    public void verifyAssignLeave_Success() {
        String employeeName = "Script Automation Tester";
        String leaveType = "US - Vacation";
        String fromDate = "2024-12-25";
        String toDate = "2024-12-30";

        leavePage
                .navigateToSection("Assign Leave")
                .clickAndSelectDropdown("Leave Type", leaveType)
                .selectDate("From Date", fromDate)
                .selectDate("To Date", toDate)
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .clickAndSelectDropdown("Partial Days", "All Day")
               .clickAndSelectDropdown("Duration", "Half Day - Morning")
                .typeInDynamicTextArea("Comments", "Assigned via Automation")
                .clickToSubmit();
        leavePage
                .clickToOK()
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "TC02 - Assign Leave Insufficient Balance Warning")
    public void verifyAssignLeave_InsufficientBalance_Check() {
        leavePage
                .navigateToSection("Assign Leave")
                .clickToSubmit()
                .verifyFieldErrorMessage("Employee Name", "Required")
                .verifyFieldErrorMessage("Leave Type", "Required")
                .verifyFieldErrorMessage("From Date", "Required")
                .verifyFieldErrorMessage("To Date", "Required");
    }
/*
   @Test(priority = 3, description = "TC03 - Apply for Leave and Verify in My Leave")
    public void verifyApplyLeave_And_CheckMyLeave() {
        String leaveType = "US - Vacation";
        String fromDate = "2024-12-01";
        String toDate = "2024-12-05";
        leavePage
                .navigateToSection( "Apply")
                .clickAndSelectDropdown("Leave Type", leaveType)
                .selectDate("From Date", fromDate)
                .selectDate("To Date", toDate)
                .typeInDynamicTextArea("Comments", "Personal Trip")
                .clickToSave()
                .verifySuccessMessage();
        leavePage
                .navigateToSection( "My Leave")
                .selectDate("From Date", fromDate)
                .selectDate("To Date", toDate)
                .clickToSubmit()
                .verifyRecordExists("Pending Approval");
    }

 */

    @Test(priority = 4, description = "TC04 - Admin Approve Leave Request")
    public void verifyLeaveList_ApproveRequest() {
        String employeeName = "Script Automation Tester";

        leavePage
                .navigateToSection("Leave List")
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .clickAndSelectDropdown("Show Leave with Status", "Pending Approval")
                .clickToSubmit()
                .verifyInfoToast();
    }
/*
    @Test(priority = 5, description = "TC05 - End-to-End: Apply for Leave and Approve it")
    public void verifyLeave_EndToEnd_Flow() {
        String leaveType = "US - Vacation";
        String fromDate = "2024-12-10";
        String toDate = "2024-12-11";
        leavePage
                .navigateToSection("Apply")
                .clickAndSelectDropdown("Leave Type", leaveType)
                .selectDate("From Date", fromDate)
                .selectDate("To Date", toDate)
                .typeInDynamicTextArea("Comments", "E2E Test")
                .clickToSave()
                .verifySuccessMessage();
        leavePage
                .navigateToSection("Leave List")
                .selectDate("From Date", fromDate)
                .selectDate("To Date", toDate)
                .clickCheckbox("Pending Approval")
                .clickToSubmit()
                .verifyRecordExists("Pending Approval");
        leavePage
                .clickActionStatus("Approve")
                .verifySuccessMessage();
        leavePage
                .clickToSubmit()
                .verifyRecordExists("Scheduled");
    }
 */

}