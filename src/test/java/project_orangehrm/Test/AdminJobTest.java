package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.AdminPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class AdminJobTest extends BaseTest {

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
                .enterPassword("admin123").clickLogin();
        dashboardPage
                .verifyDashboard("Dashboard");
        dashboardPage
                .navigateToModule("Admin");
    }

    @Test(priority = 1, groups = {"foundation"}, description = "TC01 - Verify Job Title Lifecycle: Create, Verify, and Delete")
    public void verifyJobTitle_Lifecycle_Success() {
        adminPage
                .navigateToSection("Job", "Job Titles")
                .clickToAdd()
                .typeInDynamicField("Job Title", "Principal SDET Engineer")
                .typeInDynamicTextArea("Job Description", "Responsible for Test Architecture")
                .typeInDynamicTextArea("Note", "Created by Auto-Test")
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists("Principal SDET Engineer")
                .deleteSpecificValue("Principal SDET Engineer")
                .verifySuccessMessage();
    }

    @Test(priority = 2, groups = {"foundation"}, description = "TC02 - Verify validation error for empty Job Title")
    public void verifyJobTitle_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Job", "Job Titles")
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Job Title", "Required");
    }

    @Test(priority = 3, description = "TC03 - Verify error when creating a duplicate Job Title")
    public void verifyJobTitle_DuplicateEntry_ShowError() {
        adminPage
                .navigateToSection("Job", "Job Titles")
                .clickToAdd()
                .typeInDynamicField("Job Title", "Duplicate Job Title")
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .clickToAdd()
                .typeInDynamicField("Job Title", "Duplicate Job Title")
                .clickToSave()
                .verifyFieldErrorMessage("Job Title", "Required");
        adminPage
                .navigateToSection("Job", "Job Titles")
                .deleteSpecificValue("Duplicate Job Title");
    }

    @Test(priority = 4, description = "TC04 - Verify Pay Grade Lifecycle with Currency: Create, Add Currency, Delete")
    public void verifyPayGrade_WithCurrency_Success() {
        adminPage
                .navigateToSection("Job", "Pay Grades")
                .clickToAdd()
                .typeInDynamicField("Name", "Grade 5 - Executive")
                .clickToSave()
                .verifyInfoMessage()
                .clickToAdd()
                .clickAndSelectDropdown("Currency", "EUR - Euro")
                .typeInDynamicField("Minimum Salary", "4000")
                .typeInDynamicField("Maximum Salary", "8000")
                .clickToSave()
                .verifySuccessMessage();
    }

    @Test(priority = 5, description = "TC05 - Verify validation error for empty Pay Grade Name")
    public void verifyPayGrade_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Job", "Pay Grades")
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required");
    }

    @Test(priority = 6, description = "TC06 - Verify error when creating a duplicate Pay Grade")
    public void verifyPayGrade_DuplicateEntry_ShowError() {
        adminPage
                .navigateToSection("Job", "Pay Grades")
                .clickToAdd()
                .typeInDynamicField("Name", "Duplicate Grade")
                .clickToSave()
                .verifyInfoMessage();

        adminPage
                .navigateToSection("Job", "Pay Grades")
                .clickToAdd()
                .typeInDynamicField("Name", "Duplicate Grade")
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required");

        adminPage
                .navigateToSection("Job", "Pay Grades")
                .deleteSpecificValue("Duplicate Grade");
    }

    @Test(priority = 7, description = "TC07 - Verify Employment Status Lifecycle: Create and Delete")
    public void verifyEmploymentStatus_Lifecycle_Success() {
        adminPage
                .navigateToSection("Job", "Employment Status")
                .clickToAdd()
                .typeInDynamicField("Name", "Freelance - Project Based")
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists("Freelance - Project Based");
    }

    @Test(priority = 8, description = "TC08 - Verify validation error for empty Employment Status Name")
    public void verifyEmploymentStatus_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Job", "Employment Status")
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required");
    }

    @Test(priority = 9, description = "TC09 - Verify error when creating a duplicate Employment Status")
    public void verifyEmploymentStatus_DuplicateEntry_ShowError() {
        adminPage
                .navigateToSection("Job", "Employment Status")
                .clickToAdd()
                .typeInDynamicField("Name", "Duplicate Status")
                .clickToSave()
                .verifySuccessMessage();

        adminPage
                .clickToAdd()
                .typeInDynamicField("Name", "Duplicate Status")
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required");

        adminPage
                .navigateToSection("Job", "Employment Status")
                .deleteSpecificValue("Duplicate Status");
    }

    @Test(priority = 10, description = "TC10 - Verify updating an existing Job Title")
    public void verifyJobTitle_Update_Success() {
        adminPage
                .navigateToSection("Job", "Job Titles")
                .clickToAdd()
                .typeInDynamicField("Job Title", "Junior QA Automation Tester")
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .clickToEdit("Junior QA Automation Tester")
                .typeInDynamicField("Job Title", "Senior QA Automation Tester")
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .verifyRecordExists("Senior QA Automation Tester")
                .deleteSpecificValue("Senior QA Automation Tester");
    }

    @Test(priority = 11, description = "TC11 - Verify updating Pay Grade Currency Salary")
    public void verifyPayGrade_UpdateCurrency_Success() {
        adminPage
                .navigateToSection("Job", "Pay Grades")
                .clickToAdd()
                .typeInDynamicField("Name", "Grade Update Test")
                .clickToSave()
                .verifyInfoMessage();
        adminPage
                .clickToAdd()
                .clickAndSelectDropdown("Currency", "CAD - Canadian Dollar")
                .typeInDynamicField("Minimum Salary", "2000")
                .typeInDynamicField("Maximum Salary", "4000")
                 .clickToSecSave()
                .verifySuccessMessage();
        adminPage
                .clickToEdit("Canadian Dollar")
                .typeInDynamicField("Minimum Salary", "3000")
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .navigateToSection("Job", "Pay Grades")
                .deleteSpecificValue("Grade Update Test");
    }

    @Test(priority = 12, description = "TC12 - Verify updating Employment Status Name")
    public void verifyEmploymentStatus_Update_Success() {
        String oldStatus = "Freelance Part Time ";
        String newStatus = "Part Time (Contract)";

        adminPage
                .navigateToSection("Job", "Employment Status")
                .clickToAdd()
                .typeInDynamicField("Name", oldStatus)
                .clickToSave()
                .verifySuccessMessage()
                .clickToEdit(oldStatus)
                .typeInDynamicField("Name", newStatus)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordDeleted(oldStatus)
                .verifyRecordExists(newStatus)
                .deleteSpecificValue(newStatus);
    }

    @Test(priority = 13, description = "TC13 - Verify Job Category Lifecycle: Create, Verify, and Delete")
    public void verifyJobCategory_Lifecycle_Success() {
        adminPage
                .navigateToSection("Job", "Job Categories")
                .clickToAdd()
                .typeInDynamicField("Name", "Professionals - َQA Engineer")
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists("Professionals - َQA Engineer");
    }

    @Test(priority = 14, description = "TC14 - Verify updating an existing Job Category")
    public void verifyJobCategory_Update_Success() {
        adminPage
                .navigateToSection("Job", "Job Categories")
                .clickToAdd()
                .typeInDynamicField("Name", "Solutions Architect")
                .clickToSave()
                .verifySuccessMessage()
                .clickToEdit("Solutions Architect")
                .typeInDynamicField("Name", "Skilled Solutions Architect")
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordDeleted("Solutions Architect")
                .verifyRecordExists("Skilled Solutions Architect")
                .deleteSpecificValue("Skilled Solutions Architect");
    }

    @Test(priority = 15, description = "TC15 - Verify validation error for empty Job Category Name")
    public void verifyJobCategory_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Job", "Job Categories")
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required");
    }

    @Test(priority = 16, description = "TC16 - Verify error when creating a duplicate Job Category")
    public void verifyJobCategory_DuplicateEntry_ShowError() {
        String duplicateName = "Cyber Operatives";
        adminPage
                .navigateToSection("Job", "Job Categories")
                .clickToAdd()
                .typeInDynamicField("Name", duplicateName)
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .clickToAdd()
                .typeInDynamicField("Name", duplicateName)
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required");
        adminPage
                .navigateToSection("Job", "Job Categories")
                .deleteSpecificValue(duplicateName);
    }

    @Test(priority = 17, description = "TC17 - Verify Work Shift Lifecycle: Create, Verify, and Delete")
    public void verifyWorkShift_Lifecycle_Success() {
        adminPage
                .navigateToSection("Job", "Work Shifts")
                .clickToAdd()
                .typeInDynamicField("Shift Name", "Night Shift - B")
                // .typeInDynamicField("From", "09:00 PM")
                // .typeInDynamicField("To", "05:00 AM")
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists("Night Shift - B");

    }

    @Test(priority = 18, description = "TC18 - Verify updating an existing Work Shift")
    public void verifyWorkShift_Update_Success() {
        String oldShift = "Morning Shift";
        String newShift = "Morning Shift Extended";

        adminPage
                .navigateToSection("Job", "Work Shifts")
                .clickToAdd()
                .typeInDynamicField("Shift Name", "Morning Shift")
                .typeInDynamicField("From", "08:00 AM")
                .typeInDynamicField("To", "4:00 PM")
                .clickToSave()
                .verifySuccessMessage()
                .clickToEdit("Morning Shift")
                .typeInDynamicField("Shift Name", "Morning Shift Extended")
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordDeleted("Morning Shift")
                .verifyRecordExists("Morning Shift Extended")
                .deleteSpecificValue("Morning Shift Extended");
    }

    @Test(priority = 19, description = "TC19 - Verify validation error for empty Work Shift Name")
    public void verifyWorkShift_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Job", "Work Shifts")
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Shift Name", "Required");
    }

    @Test(priority = 20, description = "TC20 - Verify error when creating a duplicate Work Shift")
    public void verifyWorkShift_DuplicateEntry_ShowError() {
        adminPage
                .navigateToSection("Job", "Work Shifts")
                .clickToAdd()
                .typeInDynamicField("Shift Name", "Standard Day")
                .typeInDynamicField("From", "09:00 AM")
                .typeInDynamicField("To", "05:00 PM")
                .clickToSave()
                .verifySuccessMessage();

        adminPage
                .clickToAdd()
                .typeInDynamicField("Shift Name", "Standard Day")
                .typeInDynamicField("From", "09:00 AM")
                .typeInDynamicField("To", "05:00 PM")
                .clickToSave()
                .verifyFieldErrorMessage("Shift Name", "Already exists");

        adminPage
                .navigateToSection("Job", "Work Shifts")
                .deleteSpecificValue("Standard Day");
    }
}