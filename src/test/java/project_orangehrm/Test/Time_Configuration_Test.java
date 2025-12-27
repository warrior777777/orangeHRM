package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.TimePage;

public class Time_Configuration_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private TimePage timePage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        timePage = new TimePage(driver);

        loginPage.
                enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .navigateToModule("Time");
    }

    @Test(priority = 1, description = "TC01 - Verify Attendance Configuration Updates")
    public void verifyAttendanceConfig_Update_Success() {
        timePage
                .navigateToSection("Attendance", "Configuration")
                .clickCheckbox("Employee can change current time when punching in/out")
                .clickCheckbox("Employee can edit/delete their own attendance records")
                .clickSave()
                .verifySuccessMessage();
    }
}