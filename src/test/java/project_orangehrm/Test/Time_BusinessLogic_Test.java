package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.TimePage;

public class Time_BusinessLogic_Test extends BaseTest {


    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private TimePage timePage;

    @BeforeMethod(alwaysRun = true)
    public void setUP(){
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

    @Test(priority = 1, description = "TC01 - Verify Error when Entering > 24 Hours in Timesheet")
    public void verifyTimesheet_InvalidHours_Error() {
        timePage
                .navigateToSection("Timesheets", "My Timesheets")
                .clickToEdit("Edit")
                .typeInTimesheetGrid("ACME Project", "Mon", "25:00")
                .clickSave();
    }

    @Test(priority = 2, description = "TC02 - Verify Cannot Punch In Future Date")
    public void verifyPunchIn_FutureDate_Error() {
        timePage
                .navigateToSection("Attendance", "Punch In/Out")
                .selectDate("Date", "2029-01-01")
                .clickPunchIn();
    }
}