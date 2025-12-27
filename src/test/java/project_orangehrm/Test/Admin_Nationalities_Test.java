package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.AdminPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class Admin_Nationalities_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private AdminPage adminPage;

    @BeforeMethod(alwaysRun = true)
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

    @Test(priority = 1, description = "TC01 - Verify Nationality Lifecycle: Create, Verify")
    public void verifyNationality_Lifecycle_Success() {
        String nationalityName = "Automation Tester Nationality";
        adminPage
                .navigateToSection("Nationalities")
                .clickToAdd()
                .typeInDynamicField("Name", nationalityName)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(nationalityName);
    }

    @Test(priority = 2, description = "TC02 - Verify Nationality Lifecycle: Create, Edit, Delete")
    public void verifyNationality_Lifecycle_Edit() {
        String nationalityName = "1-Test Nationality";
        String updatedName = "1-Updated Test Nationality";
        adminPage
                .navigateToSection("Nationalities")
                .clickToAdd()
                .typeInDynamicField("Name", nationalityName)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(nationalityName);
        adminPage
                .clickToEdit(nationalityName)
                .typeInDynamicField("Name", updatedName)
                .clickToSave()
                .verifySuccessMessage()
                .verifyRecordExists(updatedName)
                .deleteSpecificValue(updatedName)
                .verifySuccessMessage()
                .verifyRecordDeleted(updatedName);
    }

    @Test(priority = 3, description = "TC03 - Verify Nationality Validation: Empty Field")
    public void verifyNationality_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Nationalities")
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required");
    }

    @Test(priority = 4, description = "TC04 - Verify Nationality Duplicate Validation")
    public void verifyNationality_Duplicate_ShowError() {
        String duplicateName = "Egyptian";
        adminPage
                .navigateToSection("Nationalities")
                .clickToAdd()
                .typeInDynamicField("Name", duplicateName)
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Already exists");
    }

    @Test(priority = 5, description = "TC05 - Verify Nationality Search Functionality")
    public void verifyNationality_Search_Success() {
        adminPage
                .navigateToSection("Nationalities")
                .verifyRecordExists("American");
    }
}

