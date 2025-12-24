package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.AdminPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class Admin_Qualifications_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AdminPage adminPage;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        adminPage = new AdminPage(driver);

        loginPage.
                enterUsername("Admin").enterPassword("admin123").clickLogin();
        dashboardPage
                .verifyDashboard("Dashboard");
        dashboardPage
                .navigateToModule("Admin");
    }

    @Test(priority = 1, description = "TC01 - Verify Skill Lifecycle: Create")
    public void verifySkills_Lifecycle_Success() {
        String skillName = "Java Automation";
        adminPage
                .navigateToSection("Qualifications", "Skills")
                .clickToAdd()
                .typeInDynamicField("Name", skillName)
                .typeInDynamicTextArea("Description", "Backend and Frontend Testing")
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(skillName);
    }

    @Test(priority = 2, description = "TC02 - Verify Skill Lifecycle: Create,Edit and Delete")
    public void verifySkills_Lifecycle_Edit() {
        String skillName = "Python Automation";
        String updatedSkillName = "Python Playwright Automation";
        adminPage
                .navigateToSection("Qualifications", "Skills")
                .clickToAdd()
                .typeInDynamicField("Name", skillName)
                .typeInDynamicTextArea("Description", "Backend and Frontend Testing")
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(skillName);
        adminPage
                .clickToEdit(skillName)
                .typeInDynamicField("Name", updatedSkillName)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordDeleted(skillName)
                .verifyRecordExists(updatedSkillName);
        adminPage
                .deleteSpecificValue(updatedSkillName)
                .verifySuccessMessage()
                .verifyRecordDeleted(updatedSkillName);
    }


    @Test(priority = 3, description = "TC03 - Verify validation error for empty Skill Name")
    public void verifySkills_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Qualifications", "Skills")
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required");
    }

    @Test(priority = 4, description = "TC04 - Verify error when creating a duplicate Skill")
    public void verifySkills_DuplicateEntry_ShowError() {
        String duplicateSkill = "Java Scripting";
        adminPage
                .navigateToSection("Qualifications", "Skills")
                .clickToAdd()
                .typeInDynamicField("Name", duplicateSkill)
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .clickToAdd()
                .typeInDynamicField("Name", duplicateSkill)
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Already exists")
                .clickToCancel();
        adminPage
                .deleteSpecificValue(duplicateSkill);
    }

    @Test(priority = 5, description = "TC05 - Verify Education Level Lifecycle: Create")
    public void verifyEducation_Lifecycle_Success() {
        String eduLevel = "Master of Science";
        adminPage
                .navigateToSection("Qualifications", "Education")
                .clickToAdd()
                .typeInDynamicField("Level", eduLevel)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(eduLevel);
    }


    @Test(priority = 6, description = "TC06 - Verify Education Level Lifecycle: Create, Edit, Delete")
    public void verifyEducation_Lifecycle_Edit() {
        String eduLevel = "Master of artificial intelligence";
        String updatedEduLevel = "PhD in AI";
        adminPage
                .navigateToSection("Qualifications", "Education")
                .clickToAdd()
                .typeInDynamicField("Level", eduLevel)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(eduLevel);
        adminPage
                .clickToEdit(eduLevel)
                .typeInDynamicField("Level", updatedEduLevel)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(updatedEduLevel);
        adminPage
                .deleteSpecificValue(updatedEduLevel)
                .verifySuccessMessage()
                .verifyRecordDeleted(updatedEduLevel);
    }

    @Test(priority = 7, description = "TC07 - Verify validation error for empty Education Level")
    public void verifyEducation_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Qualifications", "Education")
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Level", "Required");
    }

    @Test(priority = 8, description = "TC08 - Verify error when creating a duplicate Education Level")
    public void verifyEducation_DuplicateEntry_ShowError() {
        String duplicateEdu = "Bachelor of Arts";

        adminPage
                .navigateToSection("Qualifications", "Education")
                .clickToAdd()
                .typeInDynamicField("Level", duplicateEdu)
                .clickToSave()
                .verifySuccessMessage();

        adminPage
                .clickToAdd()
                .typeInDynamicField("Level", duplicateEdu)
                .clickToSave()
                .verifyFieldErrorMessage("Level", "Already exists")
                .clickToCancel();

        adminPage
                .deleteSpecificValue(duplicateEdu)
                .verifySuccessMessage();
    }

    @Test(priority = 9, description = "TC09 - Verify License Lifecycle: Create")
    public void verifyLicenses_Lifecycle_Success() {
        String licenseName = "ISTQB Foundation";
        adminPage
                .navigateToSection("Qualifications", "Licenses")
                .clickToAdd()
                .typeInDynamicField("Name", licenseName)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(licenseName);
    }

    @Test(priority = 10, description = "TC10 - Verify License Lifecycle: Create, Edit, Delete")
    public void verifyLicenses_Lifecycle_Edit() {
        String licenseName = "OWASP Foundation";
        String updatedLicense = "OWASP Advanced";
        adminPage
                .navigateToSection("Qualifications", "Licenses")
                .clickToAdd()
                .typeInDynamicField("Name", licenseName)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(licenseName);

        adminPage
                .clickToEdit(licenseName)
                .typeInDynamicField("Name", updatedLicense)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(updatedLicense);

        adminPage
                .deleteSpecificValue(updatedLicense)
                .verifySuccessMessage();
    }


    @Test(priority = 11, description = "TC11 - Verify validation error for empty License Name")
    public void verifyLicenses_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Qualifications", "Licenses")
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required");
    }

    @Test(priority = 12, description = "TC12 - Verify error when creating a duplicate License")
    public void verifyLicenses_DuplicateEntry_ShowError() {
        String duplicateLicense = "Certified Scrum Master";

        adminPage
                .navigateToSection("Qualifications", "Licenses")
                .clickToAdd()
                .typeInDynamicField("Name", duplicateLicense)
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .clickToAdd()
                .typeInDynamicField("Name", duplicateLicense)
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Already exists")
                .clickToCancel();
        adminPage
                .deleteSpecificValue(duplicateLicense)
                .verifySuccessMessage();
    }

    @Test(priority = 13, description = "TC13 - Verify Language Lifecycle: Create")
    public void verifyLanguages_Lifecycle_Success() {
        String langName = "German-Arabic";
        adminPage
                .navigateToSection("Qualifications", "Languages")
                .clickToAdd()
                .typeInDynamicField("Name", langName)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(langName);
    }

    @Test(priority = 14, description = "TC14 - Verify Language Lifecycle: Create, Edit, Delete")
    public void verifyLanguages_Lifecycle_Edit() {
        String langName = "English-Arabic";
        String updatedLang = "Arabic (Bidi)";

        adminPage
                .navigateToSection("Qualifications", "Languages")
                .clickToAdd()
                .typeInDynamicField("Name", langName)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(langName);

        adminPage
                .clickToEdit(langName)
                .typeInDynamicField("Name", updatedLang)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(updatedLang);

        adminPage
                .deleteSpecificValue(updatedLang)
                .verifySuccessMessage()
                .verifyRecordDeleted(updatedLang);
    }


    @Test(priority = 15, description = "TC15 - Verify validation error for empty Language Name")
    public void verifyLanguages_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Qualifications", "Languages")
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required");
    }

    @Test(priority = 16, description = "TC16 - Verify error when creating a duplicate Language")
    public void verifyLanguages_DuplicateEntry_ShowError() {
        String duplicateLang = "Arabic-Bidi";

        adminPage
                .navigateToSection("Qualifications", "Languages")
                .clickToAdd()
                .typeInDynamicField("Name", duplicateLang)
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .clickToAdd()
                .typeInDynamicField("Name", duplicateLang)
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Already exists")
                .clickToCancel();

        adminPage
                .deleteSpecificValue(duplicateLang)
                .verifySuccessMessage();
    }

    @Test(priority = 17, description = "TC17 - Verify Membership Lifecycle: Create")
    public void verifyMemberships_Lifecycle_Success() {
        String membershipName = "IEEE Member";
        adminPage
                .navigateToSection("Qualifications", "Memberships")
                .clickToAdd()
                .typeInDynamicField("Name", membershipName)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(membershipName);
    }

    @Test(priority = 18, description = "TC18 - Verify Membership Lifecycle: Create, Edit, Delete")
    public void verifyMemberships_Lifecycle_Edit() {
        String membershipName = "IEEE Senior Member";
        String updatedMembership = "IEEE Associate Member";
        adminPage
                .navigateToSection("Qualifications", "Memberships")
                .clickToAdd()
                .typeInDynamicField("Name", membershipName)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(membershipName);

        adminPage
                .clickToEdit(membershipName)
                .typeInDynamicField("Name", updatedMembership)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(updatedMembership);

        adminPage
                .deleteSpecificValue(updatedMembership)
                .verifySuccessMessage()
                .verifyRecordDeleted(updatedMembership);
    }

    @Test(priority = 19, description = "TC19 - Verify validation error for empty Membership Name")
    public void verifyMemberships_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Qualifications", "Memberships")
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required");
    }

    @Test(priority = 20, description = "TC20 - Verify error when creating a duplicate Membership")
    public void verifyMemberships_DuplicateEntry_ShowError() {
        String duplicateMember = "Rotary Club";

        adminPage
                .navigateToSection("Qualifications", "Memberships")
                .clickToAdd()
                .typeInDynamicField("Name", duplicateMember)
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .clickToAdd()
                .typeInDynamicField("Name", duplicateMember)
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Already exists")
                .clickToCancel();

        adminPage
                .deleteSpecificValue(duplicateMember)
                .verifySuccessMessage();
    }
}