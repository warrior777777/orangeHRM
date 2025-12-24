package project_orangehrm.TestCleaning;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.BuzzPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class BuzzTestCleaning extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private BuzzPage buzzPage;

    @BeforeMethod
    public void setUP() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        buzzPage = new BuzzPage(driver);

        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .navigateToModule("Buzz");
    }

    @Test(priority = 1, description = "Cleanup Post - Engagement")
    public void cleanUpPost_Engagement() {
        buzzPage
                .deleteIfExists("Engagement");
    }

    @Test(priority = 2, description = "Cleanup Post - Updated Text")
    public void cleanUpPost_UpdatedText() {
        buzzPage
                .deleteIfExists("Updated Text");
    }

    @Test(priority = 3, description = "Cleanup Post - Safety Check")
    public void cleanUpPost_SafetyCheck() {
        buzzPage
                .deleteIfExists("Safety Check");
    }
}
