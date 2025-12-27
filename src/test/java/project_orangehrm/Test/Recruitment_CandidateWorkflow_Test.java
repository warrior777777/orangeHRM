package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.RecruitmentPage;

public class Recruitment_CandidateWorkflow_Test extends BaseTest {

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
        dashboardPage
                .navigateToModule("Recruitment");
    }

    @Test(priority = 1, groups = {"foundation"}, description = "TC01 - Verify Add Candidate with All Fields")
    public void verifyCandidate_Add_Success() {
        String firstName = "Workflow";
        String lastName = "Candidate";
        String email = "workflow.candidate@test.com";

        recruitmentPage
                .navigateToSection("Candidates")
                .clickToAdd()
                .typeInDynamicNameFiled("Full Name", "First Name", firstName)
                .typeInDynamicNameFiled("Full Name", "Last Name", lastName)
                .typeInDynamicField("Email", email)
                .typeInDynamicField("Contact Number", "01234567890")
                .clickAndSelectDropdown("Vacancy", "Software Engineer")
                .typeInDynamicTextArea("Notes", "Added via Automation Test")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "TC02 - Verify Add Candidate Validation")
    public void verifyCandidate_Add_Validation() {
        recruitmentPage
                .navigateToSection("Candidates")
                .clickToAdd()
                .clickSave()
                .verifyFieldErrorMessage("Full Name", "Required")
                .verifyFieldErrorMessage("Email", "Required");
    }

    @Test(priority = 3, description = "TC03 - Verify Shortlist Candidate")
    public void verifyCandidate_Shortlist_Success() {
        recruitmentPage
                .navigateToSection("Candidates")
                .typeInDynamicField("Candidate Name", "Workflow")
                .selectFromList()
                .searchUser()
                .clickEyeStatus("Workflow Candidate");
        recruitmentPage
                .clickToButton("Shortlist")
                .typeInDynamicTextArea("Notes", "Shortlisted - Good Profile")
                .clickSave()
                .verifyErrorToast();
    }

    @Test(priority = 4, groups = {"foundation"}, description = "TC04 - Verify Schedule Interview")
    public void verifyCandidate_ScheduleInterview_Success() {
        recruitmentPage
                .navigateToSection("Candidates")
                .clickAndSelectDropdown("Status", "Shortlisted")
                .searchUser()
                .clickEyeStatus("Workflow Candidate");
        recruitmentPage
                .clickToButton("Schedule Interview")
                .typeInDynamicField("Interview Title", "Technical Interview")
                .typeInDynamicField("Interviewer", "Script Automation Tester")
                .selectFromList()
                .selectDate("Date", "2025-01-20")
                .typeInDynamicTextArea("Notes", "Technical Round 1")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 5, description = "TC05 - Verify Schedule Interview Validation")
    public void verifyCandidate_ScheduleInterview_Validation() {
        recruitmentPage
                .navigateToSection("Candidates")
                .clickAndSelectDropdown("Status", "Shortlisted")
                .searchUser()
                .clickEyeStatus("Workflow Candidate");
        recruitmentPage
                .clickToButton("Schedule Interview")
                .clickSave()
                .verifyFieldErrorMessage("Interview Title", "Required")
                .verifyFieldErrorMessage("Interviewer", "Required")
                .verifyFieldErrorMessage("Date", "Required");
    }

    @Test(priority = 6, groups = {"foundation"}, description = "TC06 - Verify Mark Interview Passed")
    public void verifyCandidate_MarkInterviewPassed_Success() {
        recruitmentPage
                .navigateToSection("Candidates")
                .clickAndSelectDropdown("Status", "Interview Scheduled")
                .searchUser()
                .clickEyeStatus("Workflow Candidate");
        recruitmentPage
                .clickToButton("Mark Interview Passed")
                .typeInDynamicTextArea("Notes", "Passed - Strong technical skills")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 7, description = "TC07 - Verify Mark Interview Failed")
    public void verifyCandidate_MarkInterviewFailed_Success() {
        String firstName = "Failed";
        String lastName = "Candidate";
        String email = "failed.candidate@test.com";

        recruitmentPage
                .navigateToSection("Candidates")
                .clickToAdd()
                .typeInDynamicNameFiled("Full Name", "First Name", firstName)
                .typeInDynamicNameFiled("Full Name", "Last Name", lastName)
                .typeInDynamicField("Email", email)
                .clickSave()
                .verifySuccessMessage();
        recruitmentPage
                .clickToButton("Shortlist")
                .typeInDynamicTextArea("Notes", "Shortlisted")
                .clickSave();
        recruitmentPage
                .clickToButton("Schedule Interview")
                .typeInDynamicField("Interview Title", "Technical")
                .typeInDynamicField("Interviewer", "Script Automation")
                .selectFromList()
                .selectDate("Date", "2025-01-21")
                .clickSave();
        recruitmentPage
                .clickToButton("Mark Interview Failed")
                .typeInDynamicTextArea("Notes", "Failed - Did not meet requirements")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 8, groups = {"foundation"}, description = "TC08 - Verify Offer Job")
    public void verifyCandidate_OfferJob_Success() {
        recruitmentPage
                .navigateToSection("Candidates")
                .clickAndSelectDropdown("Status", "Interview Passed")
                .searchUser()
                .clickEyeStatus("Workflow Candidate");
        recruitmentPage
                .clickToButton("Offer Job")
                .typeInDynamicTextArea("Notes", "Offer extended - Salary negotiated")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 9, description = "TC09 - Verify Hire Candidate")
    public void verifyCandidate_Hire_Success() {
        recruitmentPage
                .navigateToSection("Candidates")
                .clickAndSelectDropdown("Status", "Job Offered")
                .searchUser()
                .clickEyeStatus("Workflow Candidate");
        recruitmentPage
                .clickToButton("Hire")
                .typeInDynamicTextArea("Notes", "Hired - Starting next month")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 10, description = "TC10 - Verify Reject Candidate")
    public void verifyCandidate_Reject_Success() {
        String firstName = "Reject";
        String lastName = "Candidate";
        String email = "reject.candidate@test.com";

        recruitmentPage
                .navigateToSection("Candidates")
                .clickToAdd()
                .typeInDynamicNameFiled("Full Name", "First tName", firstName)
                .typeInDynamicNameFiled("Full Name", "Last Name", lastName)
                .typeInDynamicField("Email", email)
                .clickSave()
                .verifySuccessMessage();
        recruitmentPage
                .clickToButton("Reject")
                .typeInDynamicTextArea("Notes", "Rejected - Does not fit role")
                .clickSave()
                .verifySuccessMessage();
    }


    @Test(priority = 11, description = "TC11 - Verify Delete Candidate")
    public void verifyCandidate_Delete_Success() {
        recruitmentPage
                .navigateToSection("Candidates")
                .typeInDynamicField("Candidate Name", "Reject")
                .selectFromList()
                .searchUser()
                .deleteSpecificValue("Reject Candidate")
                .verifySuccessMessage();
    }

    @Test(priority = 12, description = "TC12 - Verify Search by Status")
    public void verifyCandidate_SearchByStatus_Success() {
        recruitmentPage
                .navigateToSection("Candidates")
                .clickAndSelectDropdown("Status", "Hired")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 13, description = "TC13 - Verify Search by Job Title")
    public void verifyCandidate_SearchByJobTitle_Success() {
        recruitmentPage
                .navigateToSection("Candidates")
                .clickAndSelectDropdown("Job Title", "Software Engineer")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 14, description = "TC14 - Verify Search by Vacancy")
    public void verifyCandidate_SearchByVacancy_Success() {
        recruitmentPage
                .navigateToSection("Candidates")
                .clickAndSelectDropdown("Vacancy", "Software Engineer")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 15, description = "TC15 - Verify Search by Hiring Manager")
    public void verifyCandidate_SearchByHiringManager_Success() {
        recruitmentPage
                .navigateToSection("Candidates")
                .typeInDynamicField("Hiring Manager", "Script Automation")
                .selectFromList()
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 16, description = "TC16 - Verify Reset Search Filters")
    public void verifyCandidate_ResetFilters_Success() {
        recruitmentPage
                .navigateToSection("Candidates")
                .clickAndSelectDropdown("Status", "Hired")
                .clickAndSelectDropdown("Job Title", "Software Engineer")
                .clickToReset()
                .verifySearchTable();
    }
}

