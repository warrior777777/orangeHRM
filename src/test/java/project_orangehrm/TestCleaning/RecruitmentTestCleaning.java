package project_orangehrm.TestCleaning;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.RecruitmentPage;

public class RecruitmentTestCleaning extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private RecruitmentPage recruitmentPage;

    @BeforeMethod(alwaysRun = true)
    public void setUP() {
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


    @Test(priority = 1, description = "TC01 - Cleanup Candidate - E2E Candidate")
    public void cleanUpCandidate_E2ECandidate() {
        recruitmentPage
                .navigateToSection("Candidates")
                .deleteIfExists("E2E Candidate");
    }

    @Test(priority = 2, description = "TC02 - Cleanup Candidate - Decline Candidate")
    public void cleanUpCandidate_DeclineCandidate() {
        recruitmentPage
                .navigateToSection("Candidates")
                .deleteIfExists("Decline Candidate");
    }

    @Test(priority = 3, description = "TC03 - Cleanup Vacancy - Senior Java SDET")
    public void cleanUpVacancy_SeniorJavaSDET() {
        recruitmentPage
                .navigateToSection("Vacancies")
                .deleteIfExists("Senior Java SDET");
    }

    @Test(priority = 4, description = "TC04 - Cleanup Candidate - Workflow Candidate")
    public void cleanUpCandidate_WorkflowCandidate() {
        recruitmentPage
                .navigateToSection("Candidates")
                .deleteIfExists("Workflow Candidate");
    }

    @Test(priority = 5, description = "TC05 - Cleanup Candidate - Failed Candidate")
    public void cleanUpCandidate_FailedCandidate() {
        recruitmentPage
                .navigateToSection("Candidates")
                .deleteIfExists("Failed Candidate");
    }
}
