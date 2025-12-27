package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class Dashboard_Widgets_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .verifyDashboard("Dashboard");
    }


    @Test(priority = 1, description = "TC01 - Verify Core Dashboard Widgets are Visible")
    public void verifyWidgets_Visibility() {
        dashboardPage.verifyElementVisible("Time at Work");
        dashboardPage.verifyElementVisible("My Actions");
        dashboardPage.verifyElementVisible("Quick Launch");
        dashboardPage.verifyElementVisible("Employees on Leave Today");
        dashboardPage.verifyElementVisible("Employee Distribution by Sub Unit");
        dashboardPage.verifyElementVisible("Employee Distribution by Location");
    }

    @Test(priority = 2, description = "TC02 - Verify Quick Launch Shortcuts Redirection")
    public void verifyQuickLaunch_Navigation() {
        dashboardPage
                .clickQuickLaunchItem("Assign Leave")
                .verifyPageTitle("Leave")
                .navigateToModule("Dashboard");
        dashboardPage
                .clickQuickLaunchItem("Leave List")
                .verifyPageTitle("Leave")
                .navigateToModule("Dashboard");

        dashboardPage
                .clickQuickLaunchItem("Timesheets")
                .verifyPageTitle("Time");
    }

    @Test(priority = 3, description = "TC03 - Verify Time at Work Stopwatch Integration")
    public void verifyTimeWidget_ClockAction() {
        dashboardPage
                .clickTimeClockButton()
                .verifyPageTitle("Attendance");
    }


}