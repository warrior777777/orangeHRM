package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LeavePage;
import project_orangehrm.Pages.LoginPage;

public class Leave_Entitlements_Test extends BaseTest {

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
                .enterPassword("admin123").
                clickLogin();
        dashboardPage.
                verifyDashboard("Dashboard");
        dashboardPage
                .navigateToModule("Leave");
    }

    @Test(priority = 1, description = "TC01 - Add Leave Entitlement to Employee")
    public void verifyAddEntitlement_Success() {
        String employeeName = "Script Automation Tester";
        String leaveType = "US - Vacation";
        String entitlementDays = "10.00";
        leavePage
                .navigateToSection("Entitlements", "Add Entitlements")
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .clickAndSelectDropdown("Leave Type", leaveType)
                .typeInDynamicField("Entitlement", entitlementDays)
                .clickToSave()
                .clickConfirm()
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "TC02 - Verify Add Entitlement Validation")
    public void verifyAddEntitlement_Validation_Error() {
        leavePage
                .navigateToSection("Entitlements", "Add Entitlements")
                .clickToSave()
                .verifyFieldErrorMessage("Employee Name", "Required")
                .verifyFieldErrorMessage("Leave Type", "Required")
                .verifyFieldErrorMessage("Entitlement", "Required");
    }

    @Test(priority = 3, description = "TC03 - Search Employee Entitlements")
    public void verifyEmployeeEntitlements_Search_Success() {
        String employeeName = "Script Automation Tester";

        leavePage
                .navigateToSection("Entitlements", "Employee Entitlements")
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .clickToSubmit()
                .verifyRecordExists("US - Vacation");
    }
}