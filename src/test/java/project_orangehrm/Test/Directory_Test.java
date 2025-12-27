package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.DirectoryPage;
import project_orangehrm.Pages.LoginPage;

public class Directory_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private DirectoryPage directoryPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        directoryPage = new DirectoryPage(driver);

        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .verifyDashboard("Dashboard");
        dashboardPage
                .navigateToModule("Directory");
    }

    @Test(priority = 1, description = "TC01 - Verify Directory Page Display")
    public void verifyDirectory_Display_Success() {
        directoryPage
                .verifyDirectoryPage("Directory");
    }

    @Test(priority = 2, description = "TC02 - Verify Directory Search by Employee Name")
    public void verifyDirectory_SearchByName_Success() {
        String employeeName = "Script Automation";

        directoryPage
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .searchUser()
                .verifyRecordExists("Script Automation Tester");
    }

    @Test(priority = 3, description = "TC03 - Verify Directory Search by Job Title")
    public void verifyDirectory_SearchByJobTitle_Success() {
        directoryPage
                .clickAndSelectDropdown("Job Title", "QA Engineer")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 4, description = "TC04 - Verify Directory Search by Location")
    public void verifyDirectory_SearchByLocation_Success() {
        directoryPage
                .clickAndSelectDropdown("Location", "Texas R&D")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 5, description = "TC05 - Verify Directory Multiple Filters")
    public void verifyDirectory_MultipleFilters_Success() {
        directoryPage
                .clickAndSelectDropdown("Job Title", "QA Engineer")
                .clickAndSelectDropdown("Location", "Texas R&D")
                .searchUser()
                .verifySearchTable();
    }

    @Test(priority = 6, description = "TC06 - Verify Directory Reset Filters")
    public void verifyDirectory_ResetFilters_Success() {
        directoryPage
                .typeInDynamicField("Employee Name", "Test")
                .clickAndSelectDropdown("Job Title", "QA Engineer")
                .clickToReset()
                .verifySearchTable();
    }

    @Test(priority = 7, description = "TC07 - Verify Directory View Employee Details")
    public void verifyDirectory_ViewEmployeeDetails_Success() {
        String employeeName = "Script Automation";

        directoryPage
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .searchUser()
                .clickEmployeeCard("Script Automation Tester")
                .verifyEmployeeDetailsPopup();
    }

    @Test(priority = 8, description = "TC08 - Verify Directory Empty Search Results")
    public void verifyDirectory_EmptyResults_Success() {
        directoryPage
                .typeInDynamicField("Employee Name", "NonExistentEmployee12345")
                .searchUser()
                .verifyNoRecordsFound();
    }

    @Test(priority = 9, description = "TC09 - Verify Directory Contact Information Display")
    public void verifyDirectory_ContactInfo_Display() {
        String employeeName = "Script Automation";

        directoryPage
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .searchUser()
                .verifyContactInfoVisible();
    }

    @Test(priority = 10, description = "TC10 - Verify Directory Search by Sub Unit")
    public void verifyDirectory_SearchBySubUnit_Success() {
        directoryPage
                .clickAndSelectDropdown("Sub Unit", "Engineering")
                .searchUser()
                .verifySearchTable();
    }
}

