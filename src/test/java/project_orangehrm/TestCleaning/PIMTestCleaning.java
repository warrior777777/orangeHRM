package project_orangehrm.TestCleaning;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;
import project_orangehrm.Pages.PIMPage;

public class PIMTestCleaning extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private PIMPage pimPage;

    @BeforeMethod
    public void setUP() {

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        pimPage = new PIMPage(driver);

        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .navigateToModule("PIM");
    }


    @Test(priority = 1, description = "Cleanup Custom Field - Automation Badge ID")
    public void cleanUpCustomField_AutomationBadgeID() {
        pimPage
                .verifyPIMPage("PIM")
                .navigateToSection("Configuration", "Custom Fields")
                .verifyRecordExists("Automation Badge ID")
                .deleteSpecificValue("Automation Badge ID")
                .verifySuccessMessage();
    }


    @Test(priority = 2, description = "Cleanup Reporting Method - Weekly Scrum Report")
    public void cleanUpReportingMethod_WeeklyScrumReport() {
        pimPage
                .verifyPIMPage("PIM")
                .navigateToSection("Configuration", "Reporting Methods")
                .verifyRecordExists("Weekly Scrum Report")
                .deleteSpecificValue("Weekly Scrum Report")
                .verifySuccessMessage();
    }


    @Test(priority = 3, description = "Cleanup Termination Reason - Better Salary Opportunity")
    public void cleanUpTerminationReason_BetterSalaryOpportunity() {
        pimPage
                .verifyPIMPage("PIM")
                .navigateToSection("Configuration", "Termination Reasons")
                .verifyRecordExists("Better Salary Opportunity")
                .deleteSpecificValue("Better Salary Opportunity")
                .verifySuccessMessage();
    }

    @Test(priority = 4, description = "Cleanup Employee - Script Automation Tester (ID: 77777)")
    public void cleanUpEmployee_ScriptAutomationTester() {
        pimPage
                .verifyPIMPage("PIM")
                .navigateToSection("Employee")
                .typeInDynamicField("Employee Id", "77777")
                .clickAndSelectDropdown("Include", "Current Employees Only")
                .searchUser()
                .verifyRecordExists("77777")
                .deleteSpecificValue("77777")
                .verifySuccessMessage();
    }

    @Test(priority = 5, description = "Cleanup Employee - Dev Sec Ops (ID: 0777)")
    public void cleanUpEmployee_DevSecOps() {
        pimPage
                .verifyPIMPage("PIM")
                .navigateToSection("Employee")
                .typeInDynamicField("Employee Id", "0777")
                .clickAndSelectDropdown("Include", "Current Employees Only")
                .searchUser()
                .verifyRecordExists("0777")
                .deleteSpecificValue("0777")
                .verifySuccessMessage();
    }

    @Test(priority = 6, description = "Cleanup Employee - Dev Ops Trem (ID: 0333)")
    public void cleanUpEmployee_DevOpsTrem() {
        pimPage
                .verifyPIMPage("PIM")
                .navigateToSection("Employee")
                .typeInDynamicField("Employee Id", "0333")
                .clickAndSelectDropdown("Include", "Past Employees Only")
                .searchUser()
                .verifyRecordExists("0333")
                .deleteSpecificValue("0333")
                .verifySuccessMessage();
    }

    @Test(priority = 7, description = "Cleanup Report - QA Employee Audit Report")
    public void cleanUpReport_QAEmployeeAuditReport() {
        pimPage
                .verifyPIMPage("PIM")
                .navigateToSection("Reports")
                .verifyRecordExists("QA Employee Audit Report")
                .deleteSpecificValue("QA Employee Audit Report")
                .verifySuccessMessage();
    }


}
