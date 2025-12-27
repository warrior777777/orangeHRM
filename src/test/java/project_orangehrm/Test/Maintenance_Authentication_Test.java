package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.MaintenancePage;

public class Maintenance_Authentication_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private MaintenancePage maintenancePage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        maintenancePage = new MaintenancePage(driver);

        loginPage.enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
    }

    @Test(priority = 1, description = "TC01 - Verify Maintenance Access Requires Password")
    public void verifyMaintenance_Access_Security() {
        dashboardPage.navigateToModule("Maintenance");

        maintenancePage
                .verifyAccessPassword("WrongPass");
        // maintenancePage
        //  .verifyErrorMessage("Invalid credentials");
        maintenancePage
                .verifyAccessPassword("admin123");
        maintenancePage
                .verifyPageTitle("Purge Records");
    }
}