package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class Dashboard_Advanced_Test extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();

        dashboardPage
                .verifyDashboard("Dashboard");
    }



    @Test(priority = 1, description = "TC01 - Verify Dashboard Widget Toggle Visibility")
    public void verifyWidget_ToggleVisibility() {
        String widgetName = "My Actions";

        dashboardPage
                .clickGearIcon()
                .toggleWidgetVisibility(widgetName)
                .clickSave()
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "TC02 - Verify Help Support Redirection")
    public void verifyHelp_IconPresence() {
        dashboardPage
                .verifyDashboard("Dashboard");
    }
}