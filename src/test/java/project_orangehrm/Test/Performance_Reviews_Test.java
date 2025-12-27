package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.PerformancePage;

public class Performance_Reviews_Test extends BaseTest {

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

    @Test(priority = 1, description = "TC01 - Manage Review Lifecycle: Create, Activate, Delete")
    public void verifyReview_Lifecycle_Success() {
        String employeeName = "Dev  Ops";
        String supervisorName = "Script Automation Tester";
        performancePage
                .navigateToSection("Manage Reviews", "Manage Reviews")
                .clickToAdd()
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .typeInDynamicField("Supervisor Reviewer", supervisorName)
                .selectFromList()
                .typeInDynamicField("Start Date", "2024-01-01")
                .typeInDynamicField("End Date", "2024-12-31")
                .typeInDynamicField("Due Date", "2025-01-15")
                .clickSave()
                .verifySuccessMessage();
        performancePage
                .verifyRecordExists(employeeName);
        performancePage
                .deleteSpecificValue(employeeName)
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "TC02 - Verify Review Validation Errors")
    public void verifyReview_EmptyFields_Error() {
        performancePage
                .navigateToSection("Manage Reviews", "Manage Reviews")
                .clickToAdd()
                .clickSave()
                .verifyFieldErrorMessage("Employee Name", "Required")
                .verifyFieldErrorMessage("Supervisor Reviewer", "Required");
    }
}