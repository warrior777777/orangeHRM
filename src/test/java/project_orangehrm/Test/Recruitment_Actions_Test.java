package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.RecruitmentPage;

public class Recruitment_Actions_Test extends BaseTest {

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

    @Test(priority = 1, groups = {"foundation"}, description = "TC01 - Verify Reject Candidate Workflow")
    public void verifyCandidate_Reject_Success() {
        String firstName = "Reject";
        String middleName = "To";
        String lastName = "User";
        String email = "reject@test.com";

        recruitmentPage
                .clickToAdd()
                .typeInDynamicNameFiled("Full Name", "First Name", firstName)
                .typeInDynamicNameFiled("Full Name", "Middle Name", middleName)
                .typeInDynamicNameFiled("Full Name", "Last Name", lastName)
                .typeInDynamicField("Email", email)
                .clickAndSelectDropdown("Vacancy","Software Engineer")
                .clickSave()
                .verifySuccessMessage();
        recruitmentPage
                .clickEditSwitch()
                .typeInDynamicTextArea("Notes", "Skills do not match")
                .clickSave()
                .clickReject()
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "TC02 - Verify Add Candidate Validation")
    public void verifyCandidate_EmptyFields_Error() {
        recruitmentPage
                .clickToAdd()
                .clickSave()
                .verifyFieldErrorMessage("Full Name", "Required")
                .verifyFieldErrorMessage("Email", "Required");
    }
}
