package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.PerformancePage;

public class Performance_MyReviews_Test extends BaseTest {

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


    @Test(priority = 14, description = "TC14 - Verify Manage Reviews Add Reviews Life Cycle")
    public void verifyManageReviews_AddReview_Success() {
        String employeeName = "Dev Sec Ops";
        String SupervisorName = "Script Automation Tester";

        performancePage
                .navigateToSection("Manage Reviews", "Manage Reviews")
                .clickToAdd();
        performancePage
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .typeInDynamicField("Supervisor Reviewer", SupervisorName)
                .selectFromList()
                .selectDate("Review Period Start Date", "2025-03-15")
                .selectDate("Review Period End Date", "2025-03-20")
                .selectDate("Due Date", "2025-03-30")
                .closePopups()
                .clickSave()
                .verifySuccessMessage();
    }


    @Test(priority = 1, description = "TC01 - Verify My Reviews Page Display")
    public void verifyMyReviews_Display_Success() {
        performancePage
                .navigateToSection("Manage Reviews", "My Reviews");
        performancePage
                .verifySearchTable();
    }

    @Test(priority = 2, description = "TC02 - Verify My Reviews Filter by Status")
    public void verifyMyReviews_FilterByStatus_Success() {
        performancePage
                .navigateToSection("Manage Reviews", "My Reviews");
        performancePage
                .clickAndSelectDropdown("Status", "Activated")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 3, description = "TC03 - Verify My Reviews Filter by Job Title")
    public void verifyMyReviews_FilterByJobTitle_Success() {
        performancePage
                .navigateToSection("Manage Reviews", "My Reviews");
        performancePage
                .clickAndSelectDropdown("Job Title", "QA Engineer")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 4, description = "TC04 - Verify My Reviews Filter by From Date")
    public void verifyMyReviews_FilterByFromDate_Success() {
        performancePage
                .navigateToSection("Manage Reviews", "My Reviews");
        performancePage
                .selectDate("From Date", "2024-01-01")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 5, description = "TC05 - Verify My Reviews Filter by To Date")
    public void verifyMyReviews_FilterByToDate_Success() {
        performancePage
                .navigateToSection("Manage Reviews", "My Reviews");
        performancePage
                .selectDate("To Date", "2025-12-31")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 6, description = "TC06 - Verify My Reviews Reset Filters")
    public void verifyMyReviews_ResetFilters_Success() {
        performancePage
                .navigateToSection("Manage Reviews", "My Reviews");
        performancePage
                .clickAndSelectDropdown("Status", "Activated")
                .selectDate("From Date", "2024-01-01")
                .clickToReset()
                .verifySearchTable();
    }

    @Test(priority = 7, description = "TC07 - Verify My Trackers Page Display")
    public void verifyMyTrackers_Display_Success() {
        performancePage
                .navigateToSection("My Trackers");
        performancePage
                .verifySearchTable();
    }

    @Test(priority = 8, description = "TC08 - Verify My Trackers Add Log")
    public void verifyMyTrackers_AddLog_Success() {
        performancePage
                .navigateToSection("My Trackers");
        performancePage
                .clickViewTracker()
                .clickToAdd()
                .typeInDynamicTextArea("Log", "Completed task - Automation Testing")
                .typeInDynamicTextArea("Achievement", "Fixed 10 bugs")
                .clickAndSelectDropdown("Positive/Negative", "Positive")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 9, description = "TC09 - Verify My Trackers Edit Log")
    public void verifyMyTrackers_EditLog_Success() {
        performancePage
                .navigateToSection("My Trackers");
        performancePage
                .clickViewTracker()
                .clickToEdit("Completed task")
                .typeInDynamicTextArea("Log", "Updated Log - Automation Testing Complete")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 10, description = "TC10 - Verify My Trackers Delete Log")
    public void verifyMyTrackers_DeleteLog_Success() {
        performancePage
                .navigateToSection("My Trackers");
        performancePage
                .clickViewTracker()
                .deleteSpecificValue("Updated Log")
                .verifySuccessMessage();
    }

    @Test(priority = 11, description = "TC11 - Verify Employee Trackers Page Display")
    public void verifyEmployeeTrackers_Display_Success() {
        performancePage
                .navigateToSection("Employee Trackers");
        performancePage
                .verifySearchTable();
    }

    @Test(priority = 12, description = "TC12 - Verify Employee Trackers Filter by Employee")
    public void verifyEmployeeTrackers_FilterByEmployee_Success() {
        String employeeName = "Script Automation";

        performancePage
                .navigateToSection("Employee Trackers");
        performancePage
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 13, description = "TC13 - Verify Employee Trackers Add Log")
    public void verifyEmployeeTrackers_AddLog_Success() {
        String employeeName = "Script Automation";

        performancePage
                .navigateToSection("Employee Trackers");
        performancePage
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .searchUser()
                .clickViewTracker()
                .clickToAdd()
                .typeInDynamicTextArea("Log", "Manager Log - Performance Review")
                .typeInDynamicTextArea("Achievement", "Met all targets")
                .clickAndSelectDropdown("Positive/Negative", "Positive")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 14, description = "TC14 - Verify Manage Reviews Search by Employee")
    public void verifyManageReviews_SearchByEmployee_Success() {
        String employeeName = "Script Automation";

        performancePage
                .navigateToSection("Manage Reviews", "Manage Reviews");
        performancePage
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 15, description = "TC15 - Verify Manage Reviews Search by Reviewer")
    public void verifyManageReviews_SearchByReviewer_Success() {
        String reviewerName = "Script Automation";

        performancePage
                .navigateToSection("Manage Reviews", "Manage Reviews");
        performancePage
                .typeInDynamicField("Reviewer", reviewerName)
                .selectFromList()
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 16, description = "TC16 - Verify Manage Reviews Filter by Status")
    public void verifyManageReviews_FilterByStatus_Success() {
        performancePage
                .navigateToSection("Manage Reviews", "Manage Reviews");
        performancePage
                .clickAndSelectDropdown("Status", "Activated")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 17, description = "TC17 - Verify Manage Reviews Activate Review")
    public void verifyManageReviews_Activate_Success() {
        String employeeName = "Dev Ops";
        String supervisorName = "Script Automation Tester";

        performancePage
                .navigateToSection("Manage Reviews", "Manage Reviews");
        performancePage
                .clickToAdd()
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .typeInDynamicField("Supervisor Reviewer", supervisorName)
                .selectFromList()
                .selectDate("Start Date", "2025-01-01")
                .selectDate("End Date", "2025-12-31")
                .selectDate("Due Date", "2026-01-15")
                .clickSave()
                .verifySuccessMessage();
        performancePage
                .clickActivateReview()
                .verifySuccessMessage();
    }

    @Test(priority = 18, description = "TC18 - Verify Manage Reviews Complete Review")
    public void verifyManageReviews_Complete_Success() {
        performancePage
                .navigateToSection("Manage Reviews", "Manage Reviews");
        performancePage
                .clickAndSelectDropdown("Status", "Activated")
                .searchUser()
                .clickViewReview()
                .submitFinalRating()
                .verifySuccessMessage();
    }

    @Test(priority = 19, description = "TC19 - Verify Manage Reviews Reset Filters")
    public void verifyManageReviews_ResetFilters_Success() {
        performancePage
                .navigateToSection("Manage Reviews", "Manage Reviews");
        performancePage
                .clickAndSelectDropdown("Status", "Activated")
                .selectDate("From Date", "2024-01-01")
                .clickToReset()
                .verifySearchTable();
    }
}

