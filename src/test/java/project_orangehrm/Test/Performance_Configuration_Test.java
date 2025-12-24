package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.PerformancePage;

public class Performance_Configuration_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PerformancePage performancePage;

    @BeforeMethod
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

    @Test(priority = 1, description = "TC01 - Verify KPI Lifecycle: Create, Link to Job")
    public void verifyKPI_Lifecycle_Success() {
        String kpiName = "Zero Production Bugs";
        String jobTitle = "QA Engineer";

        performancePage
                .navigateToSection("Configure", "KPIs")
                .clickToAdd()
                .typeInDynamicField("Key Performance Indicator", kpiName)
                .clickAndSelectDropdown("Job Title", jobTitle)
                .typeInDynamicField("Minimum Rating", "30")
                .typeInDynamicField("Maximum Rating", "100")
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists(kpiName);
    }

    @Test(priority = 2, description = "TC02 - Verify KPI Lifecycle: Create, Link to Job, Edit, Delete")
    public void verifyKPI_Lifecycle_Edit() {
        String kpiName = "Zero Bugs, Zero Compromise";
        String jobTitle = "QA Engineer";

        performancePage
                .navigateToSection("Configure", "KPIs");
        performancePage
                .clickToAdd()
                .typeInDynamicField("Key Performance Indicator", kpiName)
                .clickAndSelectDropdown("Job Title", jobTitle)
                .typeInDynamicField("Minimum Rating", "70")
                .typeInDynamicField("Maximum Rating", "100")
                .clickSave()
                .verifySuccessMessage();
        performancePage
                .clickToEdit(kpiName)
                .typeInDynamicField("Key Performance Indicator", "Zero Compromise")
                .clickSave()
                .verifySuccessMessage();
        performancePage
                .verifyRecordExists("Zero Compromise")
                .deleteSpecificValue("Zero Compromise")
                .verifySuccessMessage()
                .verifyRecordDeleted("Zero Compromise");
    }

    @Test(priority = 3, description = "TC03 - Verify KPI Validation Errors")
    public void verifyKPI_EmptyFields_Error() {
        performancePage
                .navigateToSection("Configure", "KPIs")
                .clickToAdd()
                .clickSave()
                .verifyFieldErrorMessage("Key Performance Indicator", "Required")
                .verifyFieldErrorMessage("Job Title", "Required");
    }

    @Test(priority = 3, description = "TC03 - Verify Tracker Lifecycle: Create")
    public void verifyTracker_Lifecycle_Success() {
        String trackerName = "Quality Tracker 2025";
        String employeeName = "Script Automation Tester";
        String reviewerName = "Dev Ops";
        performancePage
                .navigateToSection("Configure", "Trackers")
                .clickToAdd()
                .typeInDynamicField("Tracker Name", trackerName)
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .typeInDynamicField("Reviewers", reviewerName)
                .selectFromList()
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 4, description = "TC04 - Verify KPI Validation Errors")
    public void verifyTracker_EmptyFields_Error() {
        performancePage
                .navigateToSection("Configure", "Trackers")
                .clickToAdd()
                .clickSave()
                .verifyFieldErrorMessage("Tracker Name", "Required")
                .verifyFieldErrorMessage("Employee Name", "Required")
                .verifyFieldErrorMessage("Reviewers", "Required");
    }

/*
    @Test(priority = 3, description = "TC03 - Verify Competency Lifecycle: Create and Link to Job")
    public void verifyCompetency_Lifecycle_Success() {
        String compName = "Leadership Skills";
        performancePage
                .navigateToSection("Configure", "Competencies")
                .clickToAdd()
                .typeInDynamicField("Name", compName)
                .typeInDynamicTextArea("Description", "Ability to lead a team")
                .clickSave()
                .verifySuccessMessage();
        performancePage
                .verifyRecordExists(compName)
                .deleteSpecificValue(compName)
                .verifySuccessMessage();
    }

 */
}
