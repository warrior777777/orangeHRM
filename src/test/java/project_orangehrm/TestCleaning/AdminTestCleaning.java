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

    @BeforeMethod(alwaysRun = true)
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


    @Test(priority = 1, description = "TC01 - Cleanup Job Title - Principal SDET Engineer")
    public void cleanUpJobTitle() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Job","Job Titles")
                .deleteIfExists("Principal SDET Engineer");
    }

    @Test(priority = 2, description = "TC02 - Cleanup Pay Grade - Grade 5 - Executive")
    public void cleanUpPayGrade() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Job", "Pay Grades")
                .deleteIfExists("Grade 5 - Executive");
    }

    @Test(priority = 3, description = "TC03 - Cleanup Employment Status - Freelance - Project Based")
    public void cleanUpEmploymentStatus() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Job", "Employment Status")
                .deleteIfExists("Freelance - Project Based");
    }

    @Test(priority = 4, description = "TC04 - Cleanup Job Category - Professionals - QA Engineer")
    public void cleanUpJobCategory() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Job", "Job Categories")
                .deleteIfExists("Professionals - َQA Engineer");
    }

    @Test(priority = 5, description = "TC05 - Cleanup Work Shift - Night Shift - B")
    public void cleanUpWorkShift() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Job", "Work Shifts")
                .deleteIfExists("Night Shift - B");
    }

    @Test(priority = 6, description = "TC06 - Cleanup Location - Cairo Innovation Hub")
    public void cleanUpLocation() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Organization","Locations")
                .typeInDynamicField("Name", "Cairo Innovation Hub")
                .searchUser()
                .deleteIfExists("Cairo Innovation Hub");
    }

    @Test(priority = 7, description = "TC07 - Cleanup Skill - Java Automation")
    public void cleanUpSkill() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Qualifications", "Skills")
                .deleteIfExists("Java Automation");
    }

    @Test(priority = 8, description = "TC08 - Cleanup Education - Master of Science")
    public void cleanUpEducation() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Qualifications", "Education")
                .deleteIfExists("Master of Science");
    }

    @Test(priority = 9, description = "TC09 - Cleanup License - ISTQB Foundation")
    public void cleanUpLicense() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Qualifications", "Licenses")
                .deleteIfExists("ISTQB Foundation");
    }

    @Test(priority = 10, description = "TC10 - Cleanup Language - German-Arabic")
    public void cleanUpLanguage() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Qualifications", "Languages")
                .deleteIfExists("German-Arabic");
    }

    @Test(priority = 11, description = "TC11 - Cleanup Membership - IEEE Member")
    public void cleanUpMembership() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Qualifications", "Memberships")
                .deleteIfExists("IEEE Member");
    }

    @Test(priority = 12, description = "TC12 - Cleanup Email Subscription - Automation Subscriber")
    public void cleanUpEmailSubscription() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Configuration", "Email Subscriptions")
                .clickToSubscribe("Leave Applications")
                .deleteIfExists("Automation Subscriber");
    }

    @Test(priority = 13, description = "TC13 - Cleanup Nationality - Automation Tester Nationality")
    public void cleanUpNationality() {
        adminPage
                .verifyAdminPage("Admin")
                .navigateToSection("Nationalities")
                .deleteIfExists("Automation Tester Nationality");
    }
}
