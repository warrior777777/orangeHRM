package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.TimePage;

public class Time_Timesheets_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private TimePage timePage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        timePage = new TimePage(driver);

        loginPage.enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage.
                navigateToModule("Time");
    }

    @Test(priority = 1, description = "TC01 - Verify My Timesheet: Add Row, Edit Hours, Save")
    public void verifyMyTimesheet_EditAndSave() {
        String projectName = "Microsoft Ltd - Azure Project";
        String activityName = "Bug Fixes";
        timePage
                .navigateToSection("Timesheets", "My Timesheets")
                .clickToEdit("Edit")
                .typeInDynamicField("Project", projectName)
                .selectFromList()
                .clickAndSelectDropdown("Activity", activityName)
                .typeInTimesheetGrid(projectName, "Mon", "8:00")
                .typeInTimesheetGrid(projectName, "Tue", "4:30")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "TC02 - Verify Submit Timesheet (Workflow Status)")
    public void verifyTimesheet_Submit_Success() {
        timePage
                .navigateToSection("Timesheets", "My Timesheets")
                .clickActionStatus("Submit")
                .verifySuccessMessage()
                .verifyBodyContains("Submitted");
    }

    @Test(priority = 3, description = "TC03 - Verify Employee Timesheet Approval (Admin Action)")
    public void verifyEmployeeTimesheet_Approve() {
        String employeeName = "Script Automation Tester";

        timePage
                .navigateToSection("Timesheets", "Employee Timesheets")
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .searchUser()
                .clickActionStatus("Approve")
                .verifySuccessMessage()
                .verifyBodyContains("Approved");
    }
}