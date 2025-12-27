package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.PerformancePage;

public class Performance_Evaluation_Test extends BaseTest {

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



    @Test(priority = 1, description = "TC01 - Verify Employee Submitting Self Review")
    public void verifySelfReview_Submit_Success() {
        String kpiName = "Zero Production Bugs";
        String rating = "90";
        String comment = "Achieved target successfully.";

        performancePage
                .navigateToSection("Manage Reviews", "My Reviews")
                .clickActionStatus("Evaluate")
                .setRatingForKPI(kpiName, rating)
                .typeInDynamicTextArea("Comments", comment)
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "TC02 - Verify Supervisor Grading Employee Review")
    public void verifySupervisorReview_Submit_Success() {
        String employeeName = "Script Automation Tester";
        String kpiName = "Zero Production Bugs";
        String rating = "95";
        String comment = "Excellent work.";

        performancePage
                .navigateToSection("Manage Reviews", "Employee Reviews")
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .searchUser()
                .clickActionStatus("Evaluate")
                .setRatingForKPI(kpiName, rating)
                .typeInDynamicTextArea("Supervisor Comments", comment)
                .clickActionStatus("Submit")
                .verifySuccessMessage();
    }
}