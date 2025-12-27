package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.MaintenancePage;

public class Maintenance_AccessRecords_Test extends BaseTest {

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
        maintenancePage
                .verifyAccessPassword("admin123");
    }

    @Test(priority = 1, description = "TC01 - Verify Access Records Download")
    public void verifyAccessRecords_Download() {
        String employeeName = "Dev Ops Term";
        maintenancePage
                .navigateToSection("Access Records")
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .searchUser()
                .verifyBodyContains("Personal Data");
        maintenancePage
                .clickEmployeeModel("Download");
    }

    @Test(priority = 2, description = "TC02 - Verify Validation for Access Records")
    public void verifyAccessRecords_Validation_Error() {
        maintenancePage
                .navigateToSection("Access Records", "Access Records")
                .searchUser()
                .verifyFieldErrorMessage("Employee Name", "Required");
    }
}