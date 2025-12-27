package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.AdminPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class AdminOrganizationTest extends BaseTest {

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

    @Test(priority = 1, groups = {"foundation"}, description = "TC01 - Verify Location Lifecycle: Create, Verify in Table")
    public void verifyLocation_Lifecycle_Success() {
        String locName = "Cairo Innovation Hub";
        String country = "Egypt";
        String city = "New Cairo";
        adminPage
                .navigateToSection("Organization", "Locations")
                .clickToAdd()
                .typeInDynamicField("Name", locName)
                .clickAndSelectDropdown("Country", country)
                .typeInDynamicField("City", city)
                .typeInDynamicTextArea("Address", "Building 5, Tech Park")
                .typeInDynamicField("Zip/Postal Code", "11835")
                .typeInDynamicField("Phone", "+201000000000")
                .clickToSave()
                .verifySuccessToast();
        adminPage
                .typeInDynamicField("Name", locName)
                .searchUser()
                .verifyRecordExists(locName);
    }

    @Test(priority = 2, description = "TC02 - Verify updating an existing Location")
    public void verifyLocation_Update_Success() {
        String oldName = "Giza Branch";
        String newName = "Cairo Main Office";
        adminPage
                .navigateToSection("Organization", "Locations")
                .clickToAdd()
                .typeInDynamicField("Name", oldName)
                .clickAndSelectDropdown("Country", "Egypt")
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .typeInDynamicField("Name", oldName)
                .searchUser()
                .clickToEdit(oldName);
        adminPage
                .typeInDynamicField("Name", newName)
                .typeInDynamicField("State/Province", "Sheikh Zayed")
                .typeInDynamicField("Zip/Postal Code", "77777")
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .typeInDynamicField("Name", newName)
                .searchUser()
                .verifyRecordExists(newName)
                .deleteSpecificValue(newName);
    }

    @Test(priority = 3, description = "TC03 - Verify validation errors for required Location fields")
    public void verifyLocation_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Organization", "Locations")
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required")
                .verifyFieldErrorMessage("Country", "Required");
    }

    @Test(priority = 4, description = "TC04 - Verify error when creating a duplicate Location Name")
    public void verifyLocation_DuplicateEntry_ShowError() {
        String duplicateName = "Alexandria Hub";
        adminPage
                .navigateToSection("Organization", "Locations")
                .clickToAdd()
                .typeInDynamicField("Name", duplicateName)
                .clickAndSelectDropdown("Country", "Egypt")
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .clickToAdd()
                .typeInDynamicField("Name", duplicateName)
                .clickAndSelectDropdown("Country", "Egypt")
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required");
        adminPage
                .navigateToSection("Organization", "Locations")
                .deleteSpecificValue(duplicateName);
    }

    @Test(priority = 5, description = "TC05 - Verify Search by City and Country filters")
    public void verifyLocation_SearchFilters_Success() {
        String searchCity = "SearchTest City";
        String searchCountry = "Canada";
        adminPage
                .navigateToSection("Organization", "Locations")
                .clickToAdd()
                .typeInDynamicField("Name", "Filter Test Location")
                .clickAndSelectDropdown("Country", searchCountry)
                .typeInDynamicField("City", searchCity)
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .typeInDynamicField("City", searchCity)
                .clickAndSelectDropdown("Country", searchCountry)
                .searchUser()
                .verifyRecordExists("Filter Test Location");
        adminPage
                .deleteSpecificValue("Filter Test Location");
    }

    @Test(priority = 6, description = "TC06 - Verify Reset Button functionality")
    public void verifyLocation_ResetFilter_Success() {
        adminPage
                .navigateToSection("Organization", "Locations")
                .typeInDynamicField("City", "New York")
                .clickAndSelectDropdown("Country", "United States")
                .searchUser();
        adminPage
                .clickToReset();
        adminPage
                .verifySearchTable();
    }

    @Test(priority = 7, description = "TC07 - Verify Organization Unit Lifecycle: Enable Edit, Create, Verify, Delete")
    public void verifyStructure_Lifecycle_Success() {
        String unitId = "QA-UNIT-01";
        String unitName = "Quality Assurance Dept";
        adminPage
                .navigateToSection("Organization", "Structure")
                .toggleEditMode()
                .clickToAdd()
                .typeInDynamicField("Unit Id", unitId)
                .typeInDynamicField("Name", unitName)
                .typeInDynamicTextArea("Description", "Handling all Testing Activities")
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .deleteSpecificUnit("QA-UNIT-01: Quality Assurance Dept")
                .verifySuccessMessage();
    }

    @Test(priority = 8, description = "TC08 - Verify validation error for empty Unit Name in Structure")
    public void verifyStructure_EmptyFields_ShowError() {
        adminPage
                .navigateToSection("Organization", "Structure")
                .toggleEditMode()
                .clickToAdd()
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Required");
    }

    @Test(priority = 9, description = "TC09 - Verify error when creating a duplicate Unit ID")
    public void verifyStructure_DuplicateEntry_ShowError() {
        String unitId = "DEV-01";
        String unitName = "Development Dept";
        adminPage
                .navigateToSection("Organization", "Structure")
                .toggleEditMode()
                .clickToAdd()
                .typeInDynamicField("Unit Id", unitId)
                .typeInDynamicField("Name", unitName)
                .clickToSave()
                .verifySuccessMessage();
        adminPage
                .waitInSeconds(4);
        adminPage
                .clickToAdd()
                .typeInDynamicField("Unit Id", unitId)
                .typeInDynamicField("Name", unitName)
                .clickToSave()
                .verifyFieldErrorMessage("Name", "Organization unit name should be unique")
                .clickToCancel();
        adminPage
                .deleteSpecificUnit("DEV-01: Development Dept")
                .verifySuccessToast();
    }
}

