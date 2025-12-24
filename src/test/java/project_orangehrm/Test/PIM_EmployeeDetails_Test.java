package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.PIMPage;

public class PIM_EmployeeDetails_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PIMPage pimPage;

    @BeforeMethod
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

    @Test(priority = 1, description = "TC01 - Verify Personal Details View")
    public void verifyPersonalDetails_View_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script")
                .verifyElementVisible("Personal Details");
    }

    @Test(priority = 2, description = "TC02 - Verify Personal Details Edit")
    public void verifyPersonalDetails_Edit_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .typeInDynamicField("Driver's License Number", "DL123456789")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 3, description = "TC03 - Verify Contact Details Update")
    public void verifyContactDetails_Update_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .clickSideBarEmp("Contact Details")
                .typeInDynamicField("Street 1", "123 Automation Street")
                .typeInDynamicField("City", "Cairo")
                .typeInDynamicField("Mobile", "+201234567890")
                .typeInDynamicField("Work Email", "script.test@company.com")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 4, description = "TC04 - Verify Emergency Contact Add")
    public void verifyEmergencyContact_Add_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .clickSideBarEmp("Emergency Contacts")
                .clickToAdd()
                .typeInDynamicField("Name", "Emergency Contact Person")
                .typeInDynamicField("Relationship", "Spouse")
                .typeInDynamicField("Home Telephone", "+201111111111")
                .typeInDynamicField("Mobile", "+201222222222")
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists("Emergency Contact Person");
    }

    @Test(priority = 5, description = "TC05 - Verify Emergency Contact Delete")
    public void verifyEmergencyContact_Delete_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .clickSideBarEmp("Emergency Contacts")
                .deleteSpecificValue("Emergency Contact Person")
                .verifySuccessMessage();
    }

    @Test(priority = 6, description = "TC06 - Verify Dependent Add")
    public void verifyDependent_Add_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .clickSideBarEmp("Dependents")
                .clickToAdd()
                .typeInDynamicField("Name", "Test Dependent")
                .clickAndSelectDropdown("Relationship", "Child")
                .selectDate("Date of Birth", "2015-05-15")
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists("Test Dependent");
    }

    @Test(priority = 7, description = "TC07 - Verify Dependent Delete")
    public void verifyDependent_Delete_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .clickSideBarEmp("Dependents")
                .deleteSpecificValue("Test Dependent")
                .verifySuccessMessage();
    }

    @Test(priority = 8, description = "TC08 - Add Immigration Document (Passport)")
    public void addImmigrationDocument_Passport_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .clickSideBarEmp("Immigration")
                .clickToAdd()
                .clickAndSelectDropdown("Document", "Passport")
                .typeInDynamicField("Number", "P123456789")
                .selectDate("Issued Date", "2020-01-01")
                .selectDate("Expiry Date", "2030-01-01")
                .typeInDynamicField("Issued By", "Egypt")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 9, description = "TC09 - Verify Immigration Record Delete")
    public void verifyImmigration_Delete_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .clickSideBarEmp("Immigration")
                .deleteSpecificValue("P123456789")
                .verifySuccessMessage();
    }

    @Test(priority = 10, description = "TC10 - Verify Salary Component Add")
    public void verifySalary_AddComponent_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .clickSideBarEmp("Salary")
                .clickToAdd()
                .clickAndSelectDropdown("Pay Grade", "Grade 1")
                .typeInDynamicField("Salary Component", "Basic Salary")
                .clickAndSelectDropdown("Pay Frequency", "Monthly")
                .clickAndSelectDropdown("Currency", "United States Dollar")
                .typeInDynamicField("Amount", "5000")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 11, description = "TC11 - Verify Work Experience Add")
    public void verifyQualifications_WorkExperience_Add_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .clickSideBarEmp("Qualifications")
                .clickToSection("Work Experience")
                .clickToAdd()
                .typeInDynamicField("Company", "Tech Company Inc")
                .typeInDynamicField("Job Title", "Senior QA Engineer")
                .selectDate("From", "2020-01-01")
                .selectDate("To", "2023-12-31")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 12, description = "TC12 - Verify Education Add")
    public void verifyQualifications_Education_Add_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .clickSideBarEmp("Qualifications")
                .clickToSection("Education")
                .clickToAdd()
                .clickAndSelectDropdown("Level", "Bachelor's Degree")
                .typeInDynamicField("Institute", "Cairo University")
                .typeInDynamicField("Major/Specialization", "Computer Science")
                .typeInDynamicField("Year", "2019")
                .typeInDynamicField("GPA/Score", "3.8")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 13, description = "TC13 - Verify Skills Add")
    public void verifyQualifications_Skills_Add_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .clickSideBarEmp("Qualifications")
                .clickToSection("Skills")
                .clickToAdd()
                .clickAndSelectDropdown("Skill", "Java")
                .typeInDynamicField("Years of Experience", "5")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 14, description = "TC14 - Verify Languages Add")
    public void verifyQualifications_Languages_Add_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .clickSideBarEmp("Qualifications")
                .clickToSection("Languages")
                .clickToAdd()
                .clickAndSelectDropdown("Language", "English")
                .clickAndSelectDropdown("Fluency", "Writing")
                .clickAndSelectDropdown("Competency", "Good")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 15, description = "TC15 - Verify License Add")
    public void verifyQualifications_License_Add_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .clickSideBarEmp("Qualifications")
                .clickToSection("License")
                .clickToAdd()
                .clickAndSelectDropdown("License Type", "ISTQB")
                .typeInDynamicField("License Number", "ISTQB-2024-001")
                .selectDate("Issued Date", "2024-01-01")
                .selectDate("Expiry Date", "2027-01-01")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 16, description = "TC16 - Verify Membership Add")
    public void verifyMembership_Add_Success() {
        pimPage
                .navigateToSection("Employee List")
                .typeInDynamicField("Employee Name", "Script Automation")
                .selectFromList()
                .searchUser()
                .clickToEdit("Script");
        pimPage
                .clickSideBarEmp("Memberships")
                .clickToAdd()
                .clickAndSelectDropdown("Membership", "IEEE")
                .clickAndSelectDropdown("Subscription Paid By", "Company")
                .typeInDynamicField("Subscription Amount", "100")
                .clickAndSelectDropdown("Currency", "United States Dollar")
                .selectDate("Subscription Commence Date", "2024-01-01")
                .selectDate("Subscription Renewal Date", "2025-01-01")
                .clickSave()
                .verifySuccessMessage();
    }
}

