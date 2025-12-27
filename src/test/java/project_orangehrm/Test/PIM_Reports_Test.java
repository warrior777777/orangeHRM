package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.PIMPage;

public class PIM_Reports_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PIMPage pimPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        pimPage = new PIMPage(driver);

        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .verifyDashboard("Dashboard");
        dashboardPage
                .navigateToModule("PIM");
    }

    @Test(priority = 1, groups = {"foundation"}, description = "TC01 - Verify Report Lifecycle: Create, Verify")
    public void verifyReport_Lifecycle_Success() {
        String reportName = "QA Employee Audit Report";
        pimPage
                .navigateToSection("Reports")
                .clickToAdd()
                .typeInDynamicField("Report Name", reportName)
                .clickAndSelectDropdown("Selection Criteria", "Job Title")
                .clickAndSelectDropdown("Include", "Current and Past Employees")
                .clickAndSelectDropdown("Select Display Field Group", "Personal")
                .clickAndSelectDropdown("Select Display Field", "Employee Id")
                .clickPlus("Select Display Field")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "TC02 - Generate Employee Report")
    public void verifyReport_Generate_Success() {
        String reportName = "QA Employee Audit Report";
        pimPage
                .navigateToSection("Reports")
                .clickToEdit(reportName)
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 3, description = "TC03 - Verify Report Lifecycle: Create, Verify, Edit, Delete")
    public void verifyReport_Lifecycle_Edit() {
        String reportName = "QA Automation Employee Audit Report";
        pimPage
                .navigateToSection("Reports")
                .clickToAdd()
                .typeInDynamicField("Report Name", reportName)
                .clickAndSelectDropdown("Selection Criteria", "Job Title")
                .clickAndSelectDropdown("Include", "Current and Past Employees")
                .clickAndSelectDropdown("Select Display Field Group", "Personal")
                .clickAndSelectDropdown("Select Display Field", "Employee Id")
                .clickPlus("Select Display Field")
                .clickSave()
                .verifySuccessMessage();
        pimPage
                .navigateToSection("Reports")
                .clickToEdit("QA Automation Employee Audit Report")
                .typeInDynamicField("Report Name", "QA Automation All Employee Audit Report")
                .clickSave()
                .verifySuccessMessage();
        pimPage
                .deleteSpecificValue("QA Automation All Employee Audit Report")
                .verifySuccessMessage()
                .verifyRecordDeleted("QA Automation All Employee Audit Report");
    }


    @Test(priority = 4, description = "TC04 - Verify Report Validation Error")
    public void verifyReport_EmptyFields_ShowError() {
        pimPage
                .navigateToSection("Reports")
                .clickToAdd()
                .clickSave()
                .verifyFieldErrorMessage("Report Name", "Required");
    }
}