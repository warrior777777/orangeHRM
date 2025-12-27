package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.MaintenancePage;

public class Maintenance_Safety_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private MaintenancePage maintenancePage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        maintenancePage = new MaintenancePage(driver);

        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .navigateToModule("Maintenance");
    }

    @Test(priority = 1, description = "TC01 - Verify Cancel Button on Password Screen")
    public void verifyAccess_Cancel_Redirection() {
        maintenancePage
                .clickCancelPassword();
        dashboardPage
                .verifyDashboard("Dashboard");
    }

    @Test(priority = 2, description = "TC02 - Verify Canceling Purge Action in Modal")
    public void verifyPurge_Modal_Cancel() {
        String employeeName = "Terminated";
        maintenancePage
                .verifyAccessPassword("admin123");
        maintenancePage
                .navigateToSection("Purge Records", "Employee Records")
                .typeInDynamicField("Employee Name", employeeName)
                .searchUser();
        maintenancePage
                .clickFirstCheckbox()
                .clickEmployeeModel("Delete Selected")
                .verifyDialogContains("Are you sure?");
        maintenancePage
                .clickEmployeeModel("No, Cancel")
                .verifySearchTable();
    }

    @Test(priority = 3, description = "TC03 - Verify Select All Checkbox")
    public void verifyPurge_SelectAll_Functionality() {
        maintenancePage.verifyAccessPassword("admin123");
        maintenancePage
                .navigateToSection("Purge Records", "Candidate Records")
                .searchUser()
                .clickSelectAllCheckbox();
    }
}
