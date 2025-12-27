package project_orangehrm.TestCleaning;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.ClaimPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class ClaimTestCleaning extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private ClaimPage claimPage;

    @BeforeMethod(alwaysRun = true)
    public void setUP() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        claimPage = new ClaimPage(driver);

        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .navigateToModule("Claim");
    }

    @Test(priority = 1, description = "TC01 - Cleanup Event - Global QA Summit 2025")
    public void cleanUpEvent_GlobalQASummit2025() {
        claimPage
                .navigateToSection("Configuration", "Events")
                .deleteIfExists("Global QA Summit 2025");
    }
}
