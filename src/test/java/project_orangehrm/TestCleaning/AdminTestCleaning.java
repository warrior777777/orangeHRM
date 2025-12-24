package project_orangehrm.TestCleaning;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.AdminPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class AdminTestCleaning extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AdminPage adminPage;

    @BeforeMethod
    public void setUP() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        adminPage = new AdminPage(driver);

        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .navigateToModule("Admin");
    }


    @Test(priority = 1, description = "Validate and Cleanup Job Title")
    public void cleanUpJobTitle() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Job","Job Titles")
                .verifyRecordExists("Principal SDET Engineer")
                .deleteSpecificValue("Principal SDET Engineer")
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "Validate and Cleanup Pay Grade")
    public void cleanUpPayGrade() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Job", "Pay Grades")
                .verifyRecordExists("Grade 5 - Executive")
                .deleteSpecificValue("Grade 5 - Executive")
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "Cleanup Employment Status - Freelance - Project Based")
    public void cleanUpEmploymentStatus() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Job", "Employment Status")
                .verifyRecordExists("Freelance - Project Based")
                .deleteSpecificValue("Freelance - Project Based")
                .verifySuccessMessage();
    }

    @Test(priority = 3, description = "Cleanup Job Category - Professionals - QA Engineer")
    public void cleanUpJobCategory() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Job", "Job Categories")
                .verifyRecordExists("Professionals - َQA Engineer")
                .deleteSpecificValue("Professionals - َQA Engineer")
                .verifySuccessMessage();
    }

    @Test(priority = 4, description = "Cleanup Work Shift - Night Shift - B")
    public void cleanUpWorkShift() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Job", "Work Shifts")
                .verifyRecordExists("Night Shift - B")
                .deleteSpecificValue("Night Shift - B")
                .verifySuccessMessage();
    }

    @Test(priority = 6, description = "Validate and Cleanup Location")
    public void cleanUpLocation() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Organization","Locations")
                .typeInDynamicField("Name", "Cairo Innovation Hub")
                .searchUser()
                .verifyRecordExists("Cairo Innovation Hub");
        adminPage
                .deleteSpecificValue("Cairo Innovation Hub")
                .verifySuccessMessage();
    }

/** wait to Edit (Structure) notable to use dynamic locator
    @Test(priority = 7)
    public void test8() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Organization","Locations")
                .typeInDynamicField("Name", "Cairo Innovation Hub")
                .searchUser()
                .verifyRecordExists("Cairo Innovation Hub");
        adminPage
                .deleteSpecificValue("Cairo Innovation Hub")
                .verifySuccessMessage();
    }
    **/


    @Test(priority =8, description = "Validate and Cleanup Skill - Java Automation")
    public void cleanUpSkill() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Qualifications", "Skills")
                .verifyRecordExists("Java Automation")
                .deleteSpecificValue("Java Automation")
                .verifySuccessMessage();
    }

    @Test(priority = 9, description = "Cleanup Education - Master of Science")
    public void cleanUpEducation() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Qualifications", "Education")
                .verifyRecordExists("Master of Science")
                .deleteSpecificValue("Master of Science")
                .verifySuccessMessage();
    }

    @Test(priority = 10, description = "Cleanup License - ISTQB Foundation")
    public void cleanUpLicense() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Qualifications", "Licenses")
                .verifyRecordExists("ISTQB Foundation")
                .deleteSpecificValue("ISTQB Foundation")
                .verifySuccessMessage();
    }

    @Test(priority = 11, description = "Cleanup Language - German-Arabic")
    public void cleanUpLanguage() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Qualifications", "Languages")
                .verifyRecordExists("German-Arabic")
                .deleteSpecificValue("German-Arabic")
                .verifySuccessMessage();
    }

    @Test(priority = 12, description = "Cleanup Membership - IEEE Member")
    public void cleanUpMembership() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Qualifications", "Memberships")
                .verifyRecordExists("IEEE Member")
                .deleteSpecificValue("IEEE Member")
                .verifySuccessMessage();
    }

    @Test(priority = 13, description = "Cleanup Email Subscription - Automation Subscriber")
    public void cleanUpEmailSubscription() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Configuration", "Email Subscriptions")
                .clickToSubscribe("Leave Applications")
                .verifyRecordExists("Automation Subscriber")
                .deleteSpecificValue("Automation Subscriber")
                .verifySuccessMessage();
    }
}
