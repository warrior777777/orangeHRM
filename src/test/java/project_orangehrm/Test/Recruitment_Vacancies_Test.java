package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.RecruitmentPage;

public class Recruitment_Vacancies_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private RecruitmentPage recruitmentPage;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        recruitmentPage = new RecruitmentPage(driver);

        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .navigateToModule("Recruitment");
    }

    @Test(priority = 1, description = "TC01 - Verify Vacancy Lifecycle: Create, Verify, Delete")
    public void verifyVacancy_Lifecycle_Success() {
        String vacancyName = "Senior Java SDET";
        String jobTitle = "QA Engineer";
        String hiringManager = "Script Automation Tester";
        recruitmentPage
                .navigateToSection("Vacancies")
                .clickToAdd()
                .typeInDynamicField("Vacancy Name", vacancyName)
                .clickAndSelectDropdown("Job Title", jobTitle)
                .typeInDynamicTextArea("Description", "Hiring for Automation Team")
                .typeInDynamicField("Hiring Manager", hiringManager)
                .selectFromList()
                .typeInDynamicField("Number of Positions", "2")
                .clickSave()
                .verifySuccessMessage();
        recruitmentPage
                .navigateToSection("Vacancies")
                .clickAndSelectDropdown("Job Title", jobTitle)
                .searchUser()
                .verifyRecordExists(vacancyName);
    }

    @Test(priority = 2, description = "TC02 - Verify Vacancy Lifecycle: Create, Verify, Edit, Delete")
    public void verifyVacancy_Lifecycle_Edit() {
        String vacancyName = "Senior Quality Engineer";
        String jobTitle = "QA Engineer";
        String hiringManager = "Script Automation Tester";
        recruitmentPage
                .navigateToSection("Vacancies")
                .clickToAdd()
                .typeInDynamicField("Vacancy Name", vacancyName)
                .clickAndSelectDropdown("Job Title", jobTitle)
                .typeInDynamicTextArea("Description", "Hiring for Automation Team")
                .typeInDynamicField("Hiring Manager", hiringManager)
                .selectFromList()
                .typeInDynamicField("Number of Positions", "2")
                .clickSave()
                .verifySuccessMessage();
        recruitmentPage
                .typeInDynamicField("Vacancy Name", "Java Automation Lead")
                .clickSave()
                .verifySuccessMessage();
        recruitmentPage
                .navigateToSection("Vacancies")
                .clickAndSelectDropdown("Job Title", jobTitle)
                .searchUser()
                .verifyRecordExists("Java Automation Lead");
        recruitmentPage
                .deleteSpecificValue("Java Automation Lead")
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "TC02 - Verify Vacancy Validation Errors")
    public void verifyVacancy_EmptyFields_Error() {
        recruitmentPage
                .navigateToSection("Vacancies")
                .clickToAdd()
                .clickSave()
                .verifyFieldErrorMessage("Vacancy Name", "Required")
                .verifyFieldErrorMessage("Job Title", "Required")
                .verifyFieldErrorMessage("Hiring Manager", "Required");
    }
}