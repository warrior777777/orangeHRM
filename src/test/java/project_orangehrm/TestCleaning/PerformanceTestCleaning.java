package project_orangehrm.TestCleaning;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.PerformancePage;

public class PerformanceTestCleaning extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PerformancePage performancePage;

    @BeforeMethod
    public void setUP() {

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        performancePage = new PerformancePage(driver);
        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .navigateToModule("Performance");
    }

    @Test(priority = 1, description = "Cleanup KPI - Zero Production Bugs")
    public void cleanUpKPI_ZeroProductionBugs() {
        performancePage
                .navigateToSection("Configure","KPIs")
                .clickAndSelectDropdown("Job Title","QA Engineer")
                .searchUser()
                .deleteSpecificValue("Zero Production Bugs")
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "Cleanup Tracker - Quality Tracker 2025")
    public void cleanUpTracker_QualityTracker2025() {
        performancePage
                .navigateToSection("Configure", "Trackers")
                .deleteSpecificValue("Quality Tracker 2025")
                .verifySuccessMessage();
    }
}


