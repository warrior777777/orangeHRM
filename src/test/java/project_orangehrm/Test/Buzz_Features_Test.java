package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.BuzzPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class Buzz_Features_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private BuzzPage buzzPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
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

    @Test(priority = 1, description = "TC01 - Verify 'Most Liked Posts' Widget")
    public void verifyMostLiked_Widget() {
        buzzPage
                .verifyElementVisible("Most Liked Posts");
    }

    @Test(priority = 2, description = "TC02 - Verify Empty Post Validation")
    public void verifyEmptyPost_Error() {
        buzzPage
                .createPost("");
    }
}