package project_orangehrm.TestCleaning;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.TimePage;

public class TimeTestCleaning extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private TimePage timePage;

    @BeforeMethod
    public void setUP() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        timePage = new TimePage(driver);

        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .navigateToModule("Time");
    }

    @Test(priority = 1, description = "Cleanup Attendance Record - Started working on Automation")
    public void cleanUpAttendance_StartedWorkingOnAutomation() {
        timePage
                .navigateToSection("Attendance", "My Records")
                .selectDate("Date", "2024-12-15")
                .clickToSubmit()
                .deleteSpecificValue("Started working on Automation")
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "Cleanup Attendance Record - Leaving for the day")
    public void cleanUpAttendance_LeavingForTheDay() {
        timePage
                .navigateToSection("Attendance", "My Records")
                .selectDate("Date", "2024-12-30")
                .clickToSubmit()
                .deleteSpecificValue("Leaving for the day")
                .verifySuccessMessage();
    }
}
