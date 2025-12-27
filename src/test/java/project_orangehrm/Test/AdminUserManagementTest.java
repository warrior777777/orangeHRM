package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.AdminPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class AdminUserManagementTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AdminPage adminPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        adminPage = new AdminPage(driver);
        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();

        dashboardPage
                .verifyDashboard("Dashboard");
        dashboardPage
                .navigateToModule("Admin");
    }

    @Test(priority = 1, description = "TC01 - Positive: Verify Admin User Lifecycle: Create, Search, and Delete")
    public void verifyUserManagement_Lifecycle_Success() {
        adminPage
                .verifyAdminPage("Admin")
                .clickToAdd()
                .clickAndSelectDropdown("User Role", "Admin")
                .typeInDynamicField("Employee Name", "Script Automation Test")
                .selectFromList()
                .clickAndSelectDropdown("Status", "Enabled")
                .typeInDynamicField("Username", "TestAdmin_QA")
                .typeInDynamicField("Password", "Password123!")
                .typeInDynamicField("Confirm Password", "Password123!")
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .typeInDynamicField("Username", "TestAdmin_QA")
                .searchUser()
                .verifyRecordExists("TestAdmin_QA");
    }

    @Test(priority = 2, description = "TC02 - Negative: Verify validation errors for all required fields in User Form")
    public void verifyUserManagement_EmptyFields_ShowError() {
        adminPage
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("User Role", "Required")
                .verifyFieldErrorMessage("Employee Name", "Required")
                .verifyFieldErrorMessage("Status", "Required")
                .verifyFieldErrorMessage("Username", "Required")
                .verifyFieldErrorMessage("Password", "Required")
                .verifyFieldErrorMessage("Confirm Password", "Required");
    }

    @Test(priority = 3, description = "TC03 - Negative: Verify search with non-existing username")
    public void verifyUserManagement_SearchNotFound_NoRecords() {
        adminPage
                .typeInDynamicField("Username", "InvalidUser_XYZ")
                .searchUser()
                .verifyRecordDeleted("InvalidUser_XYZ");
    }

    @Test(priority = 4, description = "TC04 - Negative: Verify error message when Password and Confirm Password do not match")
    public void verifyUserManagement_PasswordMismatch_ShowError() {
        adminPage
                .clickToAdd()
                .clickAndSelectDropdown("User Role", "Admin")
                .typeInDynamicField("Employee Name", "Script Automation Test")
                .selectFromList()
                .clickAndSelectDropdown("Status", "Enabled")
                .typeInDynamicField("Username", "TestMismatch_QA")
                .typeInDynamicField("Password", "Pass1234!")
                .typeInDynamicField("Confirm Password", "WrongPass123!")
                .verifyFieldErrorMessage("Confirm Password", "Required");

    }

    @Test(priority = 5, description = "TC05 - Negative: Verify error message when Password and Confirm Password do not match")
    public void verifyUserManagement_PasswordContainMinimumNumber_ShowError() {
        adminPage
                .clickToAdd()
                .clickAndSelectDropdown("User Role", "Admin")
                .typeInDynamicField("Employee Name", "Script Automation Test")
                .selectFromList()
                .clickAndSelectDropdown("Status", "Enabled")
                .typeInDynamicField("Username", "Test_QA")
                .typeInDynamicField("Password", "PasswordQA!")
                .typeInDynamicField("Confirm Password", "PasswordQA!")
                .verifyFieldErrorMessage("Password", "Required");
    }

    @Test(priority = 6, description = "TC06 - Verify Edit User: Change Status from Enabled to Disabled")
    public void verifyUserManagement_EditUser_Success() {
        String username = "EditTest_User";
        adminPage
                .verifyAdminPage("Admin")
                .clickToAdd()
                .clickAndSelectDropdown("User Role", "ESS")
                .typeInDynamicField("Employee Name", "Script Automation Test")
                .selectFromList()
                .clickAndSelectDropdown("Status", "Enabled")
                .typeInDynamicField("Username", username)
                .typeInDynamicField("Password", "Password123!")
                .typeInDynamicField("Confirm Password", "Password123!")
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .typeInDynamicField("Username", username)
                .searchUser()
                .clickToEdit(username)
                .clickAndSelectDropdown("Status", "Disabled")
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .typeInDynamicField("Username", username)
                .clickAndSelectDropdown("Status", "Disabled")
                .searchUser()
                .verifyRecordExists(username)
                .deleteSpecificValue(username)
                .verifySuccessMessage();
    }

    @Test(priority = 7, description = "TC07 - Verify Delete User Functionality")
    public void verifyUserManagement_DeleteUser_Success() {
        String username = "DeleteTest_User";
        adminPage
                .verifyAdminPage("Admin")
                .clickToAdd()
                .clickAndSelectDropdown("User Role", "ESS")
                .typeInDynamicField("Employee Name", "Script Automation Test")
                .selectFromList()
                .clickAndSelectDropdown("Status", "Enabled")
                .typeInDynamicField("Username", username)
                .typeInDynamicField("Password", "Password123!")
                .typeInDynamicField("Confirm Password", "Password123!")
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .typeInDynamicField("Username", username)
                .searchUser()
                .deleteSpecificValue(username)
                .verifySuccessMessage()
                .verifyRecordDeleted(username);
    }

    @Test(priority = 8, description = "TC08 - Verify Search by User Role Filter")
    public void verifyUserManagement_SearchByRole_Success() {
        adminPage
                .verifyAdminPage("Admin")
                .clickAndSelectDropdown("User Role", "Admin")
                .searchUser()
                .verifyRecordExists("Admin");
    }

    @Test(priority = 9, description = "TC09 - Verify Search by Status Filter")
    public void verifyUserManagement_SearchByStatus_Success() {
        adminPage
                .verifyAdminPage("Admin")
                .clickAndSelectDropdown("Status", "Enabled")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 10, description = "TC10 - Verify Duplicate Username Error")
    public void verifyUserManagement_DuplicateUsername_ShowError() {
        adminPage
                .verifyAdminPage("Admin")
                .clickToAdd()
                .clickAndSelectDropdown("User Role", "Admin")
                .typeInDynamicField("Employee Name", "Script Automation Test")
                .selectFromList()
                .clickAndSelectDropdown("Status", "Enabled")
                .typeInDynamicField("Username", "Admin")
                .typeInDynamicField("Password", "Password123!")
                .typeInDynamicField("Confirm Password", "Password123!")
                .clickToSave()
                .verifyFieldErrorMessage("Username", "Already exists");
    }

    @Test(priority = 11, description = "TC11 - Verify Reset Search Filters")
    public void verifyUserManagement_ResetFilter_Success() {
        adminPage
                .verifyAdminPage("Admin")
                .typeInDynamicField("Username", "TestUser")
                .clickAndSelectDropdown("User Role", "Admin")
                .clickAndSelectDropdown("Status", "Enabled")
                .clickToReset()
                .verifySearchTable();
    }
}