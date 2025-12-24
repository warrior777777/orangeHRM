package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.AdminPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class Admin_Configuration_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AdminPage adminPage;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        adminPage = new AdminPage(driver);
        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .verifyDashboard("Dashboard");
        dashboardPage
                .navigateToModule("Admin");
    }

    @Test(priority = 1, description = "TC01 - Verify Email Subscription Lifecycle: Add, Verify")
    public void verifyEmailSubscription_Lifecycle_Success() {
        String subscriberName = "Automation Subscriber";
        String subscriberEmail = "selenium@automated.com";
        adminPage
                .navigateToSection("Configuration", "Email Subscriptions")
                .clickToSubscribe("Leave Applications")
                .clickToAdd()
                .typeInDynamicField("Name", subscriberName)
                .typeInDynamicField("Email", subscriberEmail)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(subscriberName);
    }

    @Test(priority = 2, description = "TC02 - Verify Email Subscription Lifecycle: Add, Verify, Edit, Delete")
    public void verifyEmailSubscription_Lifecycle_Edit() {
        String subscriberName = "Ai Subscriber";
        String subscriberEmail = "auto_script@test.com";
        String subscriberEmailSec = "automation_sub@script.com";
        adminPage
                .navigateToSection("Configuration", "Email Subscriptions")
                .clickToSubscribe("Leave Applications")
                .clickToAdd();
        adminPage
                .typeInDynamicField("Name", subscriberName)
                .typeInDynamicField("Email", subscriberEmail)
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .verifyRecordExists(subscriberName)
                .clickToEdit(subscriberName)
                .typeInDynamicField("Email",subscriberEmailSec)
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .deleteSpecificValue(subscriberName)
                .verifySuccessMessage()
                .verifyRecordDeleted(subscriberName);
    }

    @Test(priority = 3, description = "TC03 - Verify Email Subscription Validation")
    public void verifyEmailSubscription_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Configuration", "Email Subscriptions")
                .clickToSubscribe("Leave Applications")
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required")
                .verifyFieldErrorMessage("Email", "Required");
    }

    @Test(priority = 2, description = "TC02 - Verify Email Configuration: Update SMTP Settings and Validation")
    public void verifyEmailConfiguration_UpdateAndValidation() {
        adminPage
                .navigateToSection("Configuration", "Email Configuration");
        adminPage
                .typeInDynamicField("Mail Sent As", "Admin")
                .clickToSave()
                .verifyFieldErrorMessage("Mail Sent As", "Expected format: admin@example.com");
        adminPage
                .typeInDynamicField("Mail Sent As", "admin@test.com")
                .clickToRadioButton("SECURE SMTP")
                .typeInDynamicField("SMTP Host", "smtp.gmail.com")
                .typeInDynamicField("SMTP Port", "587")
                .clickToSave()
                .verifySuccessMessage();
    }

    @Test(priority = 4, description = "TC04 - Verify Localization Settings: Update Default Language and Format")
    public void verifyLocalization_Update_Success() {
        adminPage
                .navigateToSection("Configuration", "Localization");
        adminPage
                .clickAndSelectDropdown("Language", "English")
                .clickAndSelectDropdown("Date Format", "yyyy-mm-dd")
                .clickToSave()
                .verifySuccessMessage();
    }

    @Test(priority = 5, description = "TC05 - Verify Language Packages Lifecycle: Add, Verify, Delete")
    public void verifyLanguagePackages_Lifecycle_Success() {
        String langName = "Arabic (Egypt)";
        adminPage
                .navigateToSection("Configuration", "Language Packages")
                .clickToAdd()
                .clickAndSelectDropdown("Name", langName)
                .clickToSave()
                .verifySuccessMessage()
                .deleteSpecificValue(langName)
                .verifySuccessMessage()
                .verifyRecordDeleted(langName);
    }

    @Test(priority = 6, description = "TC06 - Verify Social Media Authentication Lifecycle : Create, Verify, Delete")
    public void verifySocialMediaAuthentication_Lifecycle_Success() {
        adminPage
                .navigateToSection("Configuration", "Social Media Authentication")
                .clickToAdd()
                .typeInDynamicField("Name", "Test Provider")
                .typeInDynamicField("Provider URL", "https://api.sandbox-auth.com/v1/oauth2/token")
                .typeInDynamicField("Client ID", "qa-tester-9921-XyZ")
                .typeInDynamicField("Client Secret", "sec_7f92kLp93mQn01vR_TEST_ONLY")
                .clickToSave()
                .verifySuccessMessage()
                .deleteSpecificValue("Test Provider")
                .verifySuccessMessage();
    }


    @Test(priority = 7, description = "TC07 - Verify Modules Page Load and Save")
    public void verifyModules_Configuration_Save() {
        adminPage
                .navigateToSection("Configuration", "Modules")
                .clickToSave()
                .verifySuccessMessage();
    }

    @Test(priority = 8, description = "TC08 - Verify Social Media Authentication: Add Provider Validation")
    public void verifySocialMedia_AddProvider_Validation() {
        adminPage
                .navigateToSection("Configuration", "Social Media Authentication")
                .clickToAdd()
                .typeInDynamicField("Name", "Test Provider")
                .clickToSave()
                .verifyFieldErrorMessage("Provider URL", "Required")
                .verifyFieldErrorMessage("Client ID", "Required");
    }

    @Test(priority = 9, description = "TC09 - Verify OAuth Client Lifecycle: Create, Verify, Delete")
    public void verifyOAuthClient_Lifecycle_Success() {
        String clientId = "Test_OAuth_Client";
        String redirectUri = "https://www.google.com";
        /**defect-on OrangeHrm**/

        adminPage
                .navigateToSection("Configuration", "Register OAuth Client")
                .clickToAdd()
                .typeInDynamicField("Name", clientId)
                .typeInDynamicField("Redirect URI", redirectUri)
                .toggleEditMode()
                .clickToSave()
                .verifySuccessMessage()
                /**There**/
                .navigateToSection("Configuration", "Register OAuth Client")
                /**There**/
                .verifyRecordExists(clientId)
                .deleteSpecificValue(clientId)
                .verifySuccessMessage();
    }

    @Test(priority = 10, description = "TC10 - Verify OAuth Client Validation Errors")
    public void verifyOAuthClient_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Configuration", "Register OAuth Client")
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required")
                .verifyFieldErrorMessage("Redirect URI", "Required");
    }

    @Test(priority = 11, description = "TC11 - Verify LDAP Configuration Form Validation")
    public void verifyLDAPConfiguration_Validation_Error() {
        adminPage
                .navigateToSection("Configuration", "LDAP Configuration")
                .typeInDynamicField("Host", " ")
                .typeInDynamicField("Port", " ")
                .typeInDynamicField("Distinguished Name", " ")
                .typeInDynamicField("Base Distinguished Name", " ")
                .clickToSave()
                .verifyFieldErrorMessage("Host", "Required")
                .verifyFieldErrorMessage("Port", "Required")
                .verifyFieldErrorMessage("Distinguished Name", "Required")
                .verifyFieldErrorMessage("Base Distinguished Name", "Required");
    }

    @Test(priority = 12, description = "TC12 - Verify LDAP Configuration Save")
    public void verifyLDAPConfiguration_Save_Success() {
        adminPage
                .navigateToSection("Configuration", "LDAP Configuration")
                .typeInDynamicField("Host", "ldap.test.com")
                .typeInDynamicField("Port", "777")
                .clickAndSelectDropdown("Encryption", "SSL")
                .clickAndSelectDropdown("LDAP Implementation", "Open LDAP v3")
                .typeInDynamicField("Distinguished Name", "adminorangehrm.com")
                .typeInDynamicField("Password", "SecretPass123")
                .typeInDynamicField("Base Distinguished Name", "Admin")
                .clickToSave()
                .verifySuccessMessage();
    }
}