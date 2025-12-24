package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.TimePage;

public class Time_ProjectInfo_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private TimePage timePage;

    @BeforeMethod
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



    @Test(priority = 1, description = "TC01 - Verify Customer Add")
    public void verifyCustomer_Add_Success() {
        String customerName = "Auto Test Customer";

        timePage
                .navigateToSection("Project Info", "Customers")
                .clickToAdd()
                .typeInDynamicField("Name", customerName)
                .typeInDynamicTextArea("Description", "Customer added via automation")
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists(customerName);
    }

    @Test(priority = 2, description = "TC02 - Verify Customer Edit")
    public void verifyCustomer_Edit_Success() {
        String customerName = "Edit Test Customer";
        String updatedName = "Updated Customer Name";

        timePage
                .navigateToSection("Project Info", "Customers")
                .clickToAdd()
                .typeInDynamicField("Name", customerName)
                .clickSave()
                .verifySuccessMessage();
        timePage
                .clickToEdit(customerName)
                .typeInDynamicField("Name", updatedName)
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists(updatedName)
                .deleteSpecificValue(updatedName)
                .verifySuccessMessage();
    }

    @Test(priority = 3, description = "TC03 - Verify Customer Delete")
    public void verifyCustomer_Delete_Success() {
        String customerName = "Delete Test Customer";

        timePage
                .navigateToSection("Project Info", "Customers")
                .clickToAdd()
                .typeInDynamicField("Name", customerName)
                .clickSave()
                .verifySuccessMessage();
        timePage
                .deleteSpecificValue(customerName)
                .verifySuccessMessage()
                .verifyRecordDeleted(customerName);
    }

    @Test(priority = 4, description = "TC04 - Verify Customer Validation")
    public void verifyCustomer_Validation_Error() {
        timePage
                .navigateToSection("Project Info", "Customers")
                .clickToAdd()
                .clickSave()
                .verifyFieldErrorMessage("Name", "Required");
    }

    @Test(priority = 5, description = "TC05 - Verify Customer Duplicate")
    public void verifyCustomer_Duplicate_Error() {
        String customerName = "Duplicate Customer";

        timePage
                .navigateToSection("Project Info", "Customers")
                .clickToAdd()
                .typeInDynamicField("Name", customerName)
                .clickSave()
                .verifySuccessMessage();
        timePage
                .clickToAdd()
                .typeInDynamicField("Name", customerName)
                .clickSave()
                .verifyFieldErrorMessage("Name", "Already exists");
        timePage
                .navigateToSection("Project Info", "Customers")
                .deleteSpecificValue(customerName);
    }


    @Test(priority = 6, description = "TC06 - Verify Project Add")
    public void verifyProject_Add_Success() {
        String projectName = "Automation Project";
        String customerName = "Auto Test Customer";

        timePage
                .navigateToSection("Project Info", "Projects")
                .clickToAdd()
                .typeInDynamicField("Name", projectName)
                .clickAndSelectDropdown("Customer Name", customerName)
                .typeInDynamicTextArea("Description", "Project for automation testing")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 7, description = "TC07 - Verify Project Add with Admin")
    public void verifyProject_AddWithAdmin_Success() {
        String projectName = "Project With Admin";
        String customerName = "Auto Test Customer";
        String adminName = "Script Automation Tester";

        timePage
                .navigateToSection("Project Info", "Projects")
                .clickToAdd()
                .typeInDynamicField("Name", projectName)
                .clickAndSelectDropdown("Customer Name", customerName)
                .typeInDynamicField("Project Admin", adminName)
                .selectFromList()
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 8, description = "TC08 - Verify Project Edit")
    public void verifyProject_Edit_Success() {
        String projectName = "Edit Project";
        String updatedName = "Updated Project Name";
        String customerName = "Auto Test Customer";

        timePage
                .navigateToSection("Project Info", "Projects")
                .clickToAdd()
                .typeInDynamicField("Name", projectName)
                .clickAndSelectDropdown("Customer Name", customerName)
                .clickSave()
                .verifySuccessMessage();
        timePage
                .typeInDynamicField("Project", projectName)
                .searchUser()
                .clickToEdit(projectName)
                .typeInDynamicField("Name", updatedName)
                .clickSave()
                .verifySuccessMessage();
        timePage
                .typeInDynamicField("Project", updatedName)
                .searchUser()
                .deleteSpecificValue(updatedName)
                .verifySuccessMessage();
    }

    @Test(priority = 9, description = "TC09 - Verify Project Delete")
    public void verifyProject_Delete_Success() {
        String projectName = "Delete Project";
        String customerName = "Auto Test Customer";

        timePage
                .navigateToSection("Project Info", "Projects")
                .clickToAdd()
                .typeInDynamicField("Name", projectName)
                .clickAndSelectDropdown("Customer Name", customerName)
                .clickSave()
                .verifySuccessMessage();
        timePage
                .typeInDynamicField("Project", projectName)
                .searchUser()
                .deleteSpecificValue(projectName)
                .verifySuccessMessage();
    }

    @Test(priority = 10, description = "TC10 - Verify Project Validation")
    public void verifyProject_Validation_Error() {
        timePage
                .navigateToSection("Project Info", "Projects")
                .clickToAdd()
                .clickSave()
                .verifyFieldErrorMessage("Name", "Required")
                .verifyFieldErrorMessage("Customer Name", "Required");
    }

    @Test(priority = 11, description = "TC11 - Verify Project Search by Customer")
    public void verifyProject_SearchByCustomer_Success() {
        String customerName = "Auto Test Customer";

        timePage
                .navigateToSection("Project Info", "Projects")
                .clickAndSelectDropdown("Customer Name", customerName)
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 12, description = "TC12 - Verify Project Search by Project Name")
    public void verifyProject_SearchByName_Success() {
        timePage
                .navigateToSection("Project Info", "Projects")
                .typeInDynamicField("Project", "Automation")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 13, description = "TC13 - Verify Project Search by Admin")
    public void verifyProject_SearchByAdmin_Success() {
        String adminName = "Script Automation";

        timePage
                .navigateToSection("Project Info", "Projects")
                .typeInDynamicField("Project Admin", adminName)
                .selectFromList()
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 14, description = "TC14 - Verify Project Reset Filters")
    public void verifyProject_ResetFilters_Success() {
        timePage
                .navigateToSection("Project Info", "Projects")
                .typeInDynamicField("Project", "Test")
                .clickAndSelectDropdown("Customer Name", "Auto Test Customer")
                .clickToReset()
                .verifySearchTable();
    }


    @Test(priority = 15, description = "TC15 - Verify Add Project Activity")
    public void verifyProjectActivity_Add_Success() {
        String projectName = "Automation Project";
        String activityName = "QA Testing Activity";

        timePage
                .navigateToSection("Project Info", "Projects")
                .typeInDynamicField("Project", projectName)
                .searchUser()
                .clickToEdit(projectName);
        timePage
                .clickToAdd()
                .typeInDynamicField("Name", activityName)
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists(activityName);
    }

    @Test(priority = 16, description = "TC16 - Verify Delete Project Activity")
    public void verifyProjectActivity_Delete_Success() {
        String projectName = "Automation Project";

        timePage
                .navigateToSection("Project Info", "Projects")
                .typeInDynamicField("Project", projectName)
                .searchUser()
                .clickToEdit(projectName);
        timePage
                .deleteSpecificValue("QA Testing Activity")
                .verifySuccessMessage();
    }

    @Test(priority = 17, description = "TC17 - Cleanup: Delete Test Projects and Customer")
    public void cleanup_DeleteTestData() {
        timePage
                .navigateToSection("Project Info", "Projects")
                .typeInDynamicField("Customer Name", "Auto Test Customer")
                .searchUser();
        timePage
                .deleteSpecificValue("Automation Project");
        timePage
                .deleteSpecificValue("Project With Admin");
        timePage
                .navigateToSection("Project Info", "Customers")
                .deleteSpecificValue("Auto Test Customer")
                .verifySuccessMessage();
    }
}

