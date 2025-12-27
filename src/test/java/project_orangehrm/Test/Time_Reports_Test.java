package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.TimePage;

public class Time_Reports_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private TimePage timePage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
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

    @Test(priority = 1, description = "TC01 - Generate Project Report")
    public void verifyProjectReport_Generate() {
        String projectName = "Apache Software Foundation";
        timePage
                .navigateToSection("Reports", "Project Reports")
                .typeInDynamicField("Project Name", projectName)
                .selectFromList()
                .clickView()
                .verifySearchTable();
    }

    @Test(priority = 2, description = "TC02 - Generate Employee Report")
    public void verifyEmployeeReport_Generate() {
        String employeeName = "Script Automation Tester";
        timePage
                .navigateToSection("Reports", "Employee Reports")
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .clickView()
                .verifySearchTable();
    }
}