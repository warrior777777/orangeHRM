package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.PIMPage;

public class PIM_Configuration_Test extends BaseTest {

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

    @Test(priority = 1, description = "TC01 - Verify Optional Fields Configuration Save")
    public void verifyOptionalFields_Save_Success() {
        pimPage
                .navigateToSection("Configuration", "Optional Fields")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 2 , groups = {"foundation"}, description = "TC02 - Verify Custom Field Lifecycle: Create, Verify")
    public void verifyCustomField_Lifecycle_Success() {
        String fieldName = "Automation Badge ID";

        pimPage
                .navigateToSection("Configuration", "Custom Fields")
                .clickToAdd()
                .typeInDynamicField("Field Name", fieldName)
                .clickAndSelectDropdown("Screen", "Personal Details")
                .clickAndSelectDropdown("Type", "Text or Number")
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists(fieldName);
    }

    @Test(priority = 3, description = "TC03 - Verify Custom Field Lifecycle: Create, Verify, Edit, Delete")
    public void verifyCustomField_Lifecycle_Edit() {
        String fieldName = "Automation Badge ID Edit";
        pimPage
                .navigateToSection("Configuration", "Custom Fields")
                .clickToAdd()
                .typeInDynamicField("Field Name", fieldName)
                .clickAndSelectDropdown("Screen", "Personal Details")
                .clickAndSelectDropdown("Type", "Text or Number")
                .clickSave()
                .verifySuccessMessage()
                .clickToEdit(fieldName)
                .typeInDynamicField("Field Name", "Ai Automation Badge")
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists("Ai Automation Badge")
                .deleteSpecificValue("Ai Automation Badge")
                .verifySuccessMessage()
                .verifyRecordDeleted("Ai Automation Badge");
    }

    @Test(priority = 4, description = "TC04 - Verify Custom Field Validation Errors")
    public void verifyCustomField_EmptyFields_ShowError() {
        pimPage
                .navigateToSection("Configuration", "Custom Fields")
                .clickToAdd()
                .clickSave()
                .verifyFieldErrorMessage("Field Name", "Required")
                .verifyFieldErrorMessage("Screen", "Required")
                .verifyFieldErrorMessage("Type", "Required");
    }

    @Test(priority = 5, description = "TC05 - Verify Data Import Validation (No File Selected)")
    public void verifyDataImport_Validation_Error() {
        pimPage
                .navigateToSection("Configuration", "Data Import")
                .searchUser()
                .verifyFieldErrorMessage("Select File", "Required");
    }

    @Test(priority = 6, groups = {"foundation"}, description = "TC06 - Verify Reporting Method Lifecycle: Create, Verify")
    public void verifyReportingMethod_Lifecycle_Success() {
        String methodName = "Weekly Scrum Report";
        pimPage
                .navigateToSection("Configuration", "Reporting Methods")
                .clickToAdd()
                .typeInDynamicField("Name", methodName)
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists(methodName);
    }

    @Test(priority = 7, description = "TC07 - Verify Reporting Method Lifecycle: Create, Verify, Edit, Delete")
    public void verifyReportingMethod_Lifecycle_Edit() {
        String methodName = "Weekly Scrum Report";
        pimPage
                .navigateToSection("Configuration", "Reporting Methods")
                .clickToAdd()
                .typeInDynamicField("Name", methodName)
                .clickSave()
                .verifySuccessMessage()
                .clickToEdit("Weekly Scrum Report")
                .typeInDynamicField("Name","Monthly Scrum Report")
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists("Monthly Scrum Report")
                .deleteSpecificValue("Monthly Scrum Report")
                .verifySuccessMessage()
                .verifyRecordDeleted("Monthly Scrum Report");
    }

    @Test(priority = 8, description = "TC08 - Verify Reporting Method Validation & Duplicates")
    public void verifyReportingMethod_Validation_And_Duplicate() {
        String duplicateName = "duplicateName";
        pimPage
                .navigateToSection("Configuration", "Reporting Methods")
                .clickToAdd()
                .clickSave()
                .verifyFieldErrorMessage("Name", "Required");
        pimPage
                .typeInDynamicField("Name", duplicateName)
                .clickSave()
                .verifySuccessMessage();
        pimPage
                .clickToAdd()
                .typeInDynamicField("Name", duplicateName)
                .clickSave()
                .verifyFieldErrorMessage("Name", "Already exists");
        pimPage
                .navigateToSection("Configuration", "Reporting Methods")
                .deleteSpecificValue(duplicateName);
    }

    @Test(priority = 9, groups = {"foundation"}, description = "TC09 - Verify Termination Reason Lifecycle: Create, Verify")
    public void verifyTerminationReason_Lifecycle_Success() {
        String reasonName = "Better Salary Opportunity";
        pimPage
                .navigateToSection("Configuration", "Termination Reasons")
                .clickToAdd()
                .typeInDynamicField("Name", reasonName)
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists(reasonName);
    }

    @Test(priority = 10, description = "TC10 - Verify Termination Reason Lifecycle: Create, Verify, Edit, Delete")
    public void verifyTerminationReason_Lifecycle_Edit() {
        String reasonName = "Better Salary Opportunity Edit";
        pimPage
                .navigateToSection("Configuration", "Termination Reasons")
                .clickToAdd()
                .typeInDynamicField("Name", reasonName)
                .clickSave()
                .verifySuccessMessage()
                .clickToEdit(reasonName)
                .typeInDynamicField("Name", "Better Job Opportunity")
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists("Better Job Opportunity")
                .deleteSpecificValue("Better Job Opportunity")
                .verifySuccessMessage()
                .verifyRecordDeleted("Better Job Opportunity");
    }

    @Test(priority = 11, description = "TC11 - Verify Termination Reason Validation")
    public void verifyTerminationReason_EmptyFields_ShowError() {
        pimPage
                .navigateToSection("Configuration", "Termination Reasons")
                .clickToAdd()
                .clickSave()
                .verifyFieldErrorMessage("Name", "Required");
    }
}