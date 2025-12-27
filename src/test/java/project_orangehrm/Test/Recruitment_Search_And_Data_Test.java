package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.RecruitmentPage;

public class Recruitment_Search_And_Data_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private RecruitmentPage recruitmentPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        recruitmentPage = new RecruitmentPage(driver);
        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage.
                navigateToModule("Recruitment");
    }

    @Test(priority = 1, description = "TC01 - Verify Search Candidate by Name and Status")
    public void verifyCandidate_Search_Success() {
        String candidateName = "E2E";
        recruitmentPage
                .navigateToSection("Candidates")
                .typeInDynamicField("Candidate Name", candidateName)
                .selectFromList()
                .clickAndSelectDropdown("Status", "Application Initiated")
                .searchUser()
                .verifyRecordExists(candidateName);
    }

    @Test(priority = 2, description = "TC02 - Verify Reset Search Filters")
    public void verifySearch_Reset_Success() {
        recruitmentPage
                .navigateToSection("Candidates")
                .clickAndSelectDropdown("Job Title", "Software Engineer")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 3, description = "TC03 - Verify Editing Candidate Details (Email/Phone)")
    public void verifyCandidate_EditInfo_Success() {
        String oldEmail = "wrong@test.com";
        String newEmail = "correct@test.com";
        recruitmentPage
                .clickToAdd()
                .typeInDynamicNameFiled("Full Name", "First Name", "EditMe")
                .typeInDynamicNameFiled("Full Name", "Last Name", "User")
                .typeInDynamicField("Email", oldEmail)
                .clickSave()
                .verifySuccessMessage();
        recruitmentPage
                .clickEditSwitch()
                .typeInDynamicField("Email", newEmail)
                .clickSave()
                .verifySuccessMessage();
        recruitmentPage
                .navigateToSection("Candidates")
                .typeInDynamicField("Candidate Name","EditMe")
                .selectFromList()
                .searchUser()
                .deleteSpecificValue("EditMe User")
                .verifySuccessMessage();
    }
}