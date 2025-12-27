package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.PIMPage;

public class PIM_Employee_Test extends BaseTest {

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

    @Test(priority = 1, groups = {"foundation"}, description = "TC01 - Verify Add Employee Lifecycle: Create, Search")
    public void verifyEmployee_Lifecycle_Success() {
        String FirstName = "Script";
        String MiddleName = "Automation";
        String LastName = "Tester";
        String EmpId = "77777";
        pimPage
                .navigateToSection("Add Employee");
        pimPage
                .typeInDynamicNameFiled("Employee Full Name", "First Name", FirstName)
                .typeInDynamicNameFiled("Employee Full Name", "Middle Name", MiddleName)
                .typeInDynamicNameFiled("Employee Full Name", "Last Name", LastName)
                .typeInDynamicField("Employee Id", EmpId)
                .clickSave()
                .verifySuccessMessage();
        pimPage
                .navigateToSection("Employee")
                .typeInDynamicField("Employee Id", EmpId)
                .clickAndSelectDropdown("Include", "Current Employees Only")
                .searchUser()
                .verifyRecordExists("77777");
    }

    @Test(priority = 2, groups = {"foundation"}, description = "TC02 - Verify Add Employee with Login Details (Create User Account)")
    public void verifyEmployee_WithLoginDetails_Success() {
        String firstName = "Dev";
        String MiddleName = "Sec";
        String lastName = "Ops";
        String username = "devops_user";
        String password = "Password123!";

        pimPage
                .navigateToSection("Add Employee")
                .typeInDynamicNameFiled("Employee Full Name", "First Name", firstName)
                .typeInDynamicNameFiled("Employee Full Name", "Middle Name", MiddleName)
                .typeInDynamicNameFiled("Employee Full Name", "Last Name", lastName)
                .typeInDynamicField("Employee Id", "0777")
                .toggleCreateDetails()
                .typeInDynamicField("Username", username)
                .typeInDynamicField("Password", password)
                .typeInDynamicField("Confirm Password", password)
                .clickSave()
                .verifySuccessMessage();
        pimPage
                .clickSideBarEmp("Report-to")
                .clickToSection("Assigned Supervisors")
                .typeInDynamicField("Name", "Script Automation Tester")
                .selectFromList()
                .clickAndSelectDropdown("Reporting Method", "Test_Reporting")
                .clickSave();
        pimPage
                .clickSideBarEmp("Job")
                .selectDate("Joined Date", "2025-2-15")
                .clickAndSelectDropdown("Job Title", "QA Engineer")
                .clickSave();

        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", MiddleName + " " + lastName)
                .searchUser()
                .verifyRecordExists("0777");
    }

    @Test(priority = 3, groups = {"foundation"}, description = "TC03 - Verify Add Employee with Trem")
    public void verifyEmployee_Trem() {
        String firstName = "Dev";
        String MiddleName = "Ops";
        String lastName = "Trem";

        pimPage
                .navigateToSection("Add Employee")
                .typeInDynamicNameFiled("Employee Full Name", "First Name", firstName)
                .typeInDynamicNameFiled("Employee Full Name", "Middle Name", MiddleName)
                .typeInDynamicNameFiled("Employee Full Name", "Last Name", lastName)
                .typeInDynamicField("Employee Id", "0333")
                .clickSave()
                .verifySuccessMessage();
        pimPage
                .clickSideBarEmp("Job")
                .clickTremEmp()
                .selectDate("Termination Date", "2024-09-30")
                .closePopups()
                .clickAndSelectDropdown("Termination Reason", "Other")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 4, description = "TC04 - Verify Add Employee Validation")
    public void verifyEmployee_EmptyFields_ShowError() {
        pimPage
                .navigateToSection("Add Employee")
                .clickSave()
                .verifyFieldErrorMessage("Employee Full Name", "Required");
    }

    @Test(priority = 5, description = "TC05 - Verify Search by Employment Status")
    public void verifyEmployeeList_SearchFilter_Success() {
        pimPage
                .navigateToSection("Employee List")
                .clickAndSelectDropdown("Employment Status", "Full-Time Contract")
                .searchUser()
                .verifySearchTable();
    }
}