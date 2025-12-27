package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.TimePage;

public class Time_Attendance_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private TimePage timePage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        timePage = new TimePage(driver);

        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .navigateToModule("Time");
    }

    @Test(priority = 1, description = "TC01 - Verify Punch Punch In/Out Workflow")
    public void verifyPunchIn_Out_Success() {
        timePage
                .navigateToSection("Attendance", "Punch In/Out")
                .selectDate("Date", "2024-12-15")
                .typeInDynamicTextArea("Note", "Started working on Automation")
                .clickPunchIn()
                .verifySuccessMessage();
        timePage
                .selectDate("Date", "2024-12-30")
                .typeInDynamicTextArea("Note", "Leaving for the day")
                .clickPunchOut()
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "TC02 - Verify Punch Punch In/Out Workflow")
    public void verifyPunchIn_Out_Validate() {
        timePage
                .navigateToSection("Attendance", "Punch In/Out")
                .selectDate("Date", "2024-12-15")
                .typeInDynamicTextArea("Note", "Started working on Automation")
                .clickPunchIn()
                        .verifySuccessMessage();
        timePage
                .selectDate("Date", "2024-12-30")
                .typeInDynamicTextArea("Note", "Leaving for the day")
                .clickPunchOut()
                .verifySuccessMessage();
    }

    @Test(priority = 3, description = "TC03 - Verify Punch Punch In/Out Verify, Edit, Delete Workflow")
    public void verifyPunchIn_Out_Success_Edit() {
        timePage
                .navigateToSection("Attendance", "Punch In/Out")
                .selectDate("Date", "2024-7-15")
                .typeInDynamicTextArea("Note", "Started working on Scripting")
                .clickPunchIn()
                .verifySuccessMessage();
        timePage
                .selectDate("Date", "2024-7-30")
                .typeInDynamicTextArea("Note", "Deploying to Sleep Mode")
                .clickPunchOut()
                .verifySuccessMessage();
        timePage
                .navigateToSection("Attendance", "My Records")
                .selectDate("Date", "2024-7-15")
                .clickToSubmit()
                .clickToEdit("360.00");
        timePage
                .selectDate("Date", "2024-7-27")
                .clickSave()
                .verifySuccessMessage();
        timePage
                .deleteSpecificValue("72.00")
                .verifySuccessMessage();
    }


    @Test(priority = 4, description = "TC04 - Verify Employee Record Lifecycle: Create, Edit, Delete")
    public void verifyEmployeeRecords_Search() {
        String employeeName = "Script Automation Tester";
        String searchDate = "2024-12-01";

        timePage
                .navigateToSection("Attendance", "Employee Records");
        timePage
                .typeInDynamicField("Employee Name", employeeName)
                .selectFromList()
                .selectDate("Date", searchDate)
                .searchUser()
                .verifySearchTable();
        timePage
                .clickToAdd()
                .selectDate("Date","2025-7-15")
                .typeInDynamicTextArea("Note","Code pushed System: On")
                .clickPunchIn()
                .verifySuccessMessage();
        timePage
                .clickToAdd()
                .selectDate("Date","2025-7-25")
                .typeInDynamicTextArea("Note","Code pushed System: On")
                .clickPunchOut()
                .verifySuccessMessage();
        timePage
                .selectDate("Date","2025-7-15")
                .clickView()
                .clickToEdit("240.00");
        timePage
                .selectDate("Date","2025-7-5")
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 5, description = "TC05 - Verify My Attendance Records")
    public void verifyMyRecords_View_Success() {
        String searchDate = "2025-7-30";

        timePage
                .navigateToSection("Attendance", "My Records")
                .selectDate("Date", searchDate)
                .clickView()
                .verifyRecordExists("Code pushed System: On");
    }
}