package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.MaintenancePage;

public class Maintenance_PurgeRecords_Test extends BaseTest {

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

    @Test(priority = 1, description = "TC01 - Verify Purge Employee Records Search & Delete")
    public void verifyPurgeEmployee_Lifecycle() {
        String employeeName = "Dev Ops Trem";

        maintenancePage
                .navigateToSection("Purge Records", "Employee Records")
                .typeInDynamicField("Past Employee", employeeName)
                .selectFromList()
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 2, description = "TC02 - Verify Purge Candidate Records Search & Delete")
    public void verifyPurgeCandidate_Lifecycle() {
        String vacancy = "Senior QA Engineer";
        maintenancePage
                .navigateToSection("Purge Records", "Candidate Records")
                .typeInDynamicField("Vacancy", vacancy)
                .selectFromList()
                .searchUser()
                .verifySearchTable();
    }
}
