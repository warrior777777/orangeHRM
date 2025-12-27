package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.ClaimPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class Claim_Configuration_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ClaimPage claimPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        claimPage = new ClaimPage(driver);

        loginPage
                .enterUsername("Admin").
                enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .navigateToModule("Claim");
    }

    @Test(priority = 1, description = "TC01 - Verify Event: Validate Empty Field")
    public void verifyEvent_Validate_Field() {
        claimPage
                .navigateToSection("Configuration", "Events")
                .clickToAdd()
                .typeInDynamicField("Event Name", "")
                .clickSave()
                .verifyFieldErrorMessage("Event Name", "Required");
    }

    @Test(priority = 2, description = "TC02 - Verify Event Lifecycle: Create, Verify")
    public void verifyEvent_Lifecycle_Success() {
        String eventName = "Global QA Summit 2025";

        claimPage
                .navigateToSection("Configuration", "Events")
                .clickToAdd()
                .typeInDynamicField("Event Name", eventName)
                .typeInDynamicTextArea("Description", "Annual QA Conference")
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists(eventName);
    }

    @Test(priority = 3, description = "TC03 - Verify Event Lifecycle: Create, Verify, Edit, Delete")
    public void verifyEvent_Lifecycle_Edit() {
        String eventName = "Global CyberSecurity Summit 2025";
        String newEventName = "Global Ai Summit 2025";

        claimPage
                .navigateToSection("Configuration", "Events");
        claimPage
                .clickToAdd()
                .typeInDynamicField("Event Name", eventName)
                .typeInDynamicTextArea("Description", "Annual CyberSecurity Conference")
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists(eventName);
        claimPage
                .clickToEdit(eventName)
                .typeInDynamicField("Event Name", newEventName)
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists(newEventName)
                .deleteSpecificValue(newEventName)
                .verifySuccessMessage();
    }

    @Test(priority = 4, description = "TC04 - Verify Event: Handle Duplication")
    public void verifyEvent_Duplicate_Handling() {
        String eventName = "Global Software Summit 2025";
        claimPage
                .navigateToSection("Configuration", "Events");
        claimPage
                .clickToAdd()
                .typeInDynamicField("Event Name", eventName)
                .typeInDynamicTextArea("Description", "Annual Software Conference")
                .clickSave()
                .verifySuccessMessage();
        claimPage
                .verifyRecordExists(eventName)
                .clickToAdd()
                .typeInDynamicField("Event Name", eventName)
                .clickSave()
                .verifyFieldErrorMessage("Event Name", "Already exists");
        claimPage
                .navigateToSection("Configuration", "Events")
                .deleteSpecificValue(eventName)
                .verifySuccessMessage();
    }

    @Test(priority = 5, description = "TC05 - Verify Expense Type: Validate Empty Field")
    public void verifyExpenseType_Validate_Field() {
        claimPage
                .navigateToSection("Configuration", "Expense Types")
                .clickToAdd()
                .typeInDynamicField("Name", "")
                .clickSave()
                .verifyFieldErrorMessage("Name", "Required");
    }

    @Test(priority = 6, description = "TC06 - Verify Expense Type Lifecycle: Create, Verify")
    public void verifyExpenseType_Lifecycle_Success() {
        String expenseName = "Local Transport";

        claimPage
                .navigateToSection("Configuration", "Expense Types")
                .clickToAdd()
                .typeInDynamicField("Name", expenseName)
                .typeInDynamicTextArea("Description", "Transport Reimbursement")
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists(expenseName)
                .deleteSpecificValue(expenseName)
                .verifySuccessMessage();
    }

    @Test(priority = 7, description = "TC07 - Verify Expense Type Lifecycle: Create, Edit, Delete")
    public void verifyExpenseType_Lifecycle_Edit() {
        String expenseName = "Team Lunch";
        String newExpenseName = "Client Dinner";

        claimPage
                .navigateToSection("Configuration", "Expense Types")
                .clickToAdd()
                .typeInDynamicField("Name", expenseName)
                .typeInDynamicTextArea("Description", "Monthly Team Lunch")
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists(expenseName);
        
        claimPage
                .clickToEdit(expenseName)
                .typeInDynamicField("Name", newExpenseName)
                .clickSave()
                .verifySuccessMessage()
                .verifyRecordExists(newExpenseName)
                .deleteSpecificValue(newExpenseName)
                .verifySuccessMessage();
    }

    @Test(priority = 8, description = "TC08 - Verify Expense Type: Handle Duplication")
    public void verifyExpenseType_Duplicate_Handling() {
        String expenseName = "Internet Bill";

        claimPage
                .navigateToSection("Configuration", "Expense Types")
                .clickToAdd()
                .typeInDynamicField("Name", expenseName)
                .typeInDynamicTextArea("Description", "Remote work internet")
                .clickSave()
                .verifySuccessMessage();
        claimPage
                .clickToAdd()
                .typeInDynamicField("Name", expenseName)
                .clickSave()
                .verifyFieldErrorMessage("Name", "Already exists");
        claimPage
                .navigateToSection("Configuration", "Expense Types")
                .deleteSpecificValue(expenseName)
                .verifySuccessMessage();
    }
}