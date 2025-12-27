package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.PerformancePage;

public class Performance_Reports_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PerformancePage performancePage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
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

    @Test(priority = 1, description = "TC01 - Generate Employee Performance Report")
    public void verifyPerformanceReport_Generate() {
        String employeeName = "Script Automation Tester";
        String jobTitle = "QA Engineer";

        performancePage
                .navigateToSection("Reports", "Employee Performance Report")
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .clickAndSelectDropdown("Job Title", jobTitle)
                .clickView("View")
                .verifySearchTable();
    }
}