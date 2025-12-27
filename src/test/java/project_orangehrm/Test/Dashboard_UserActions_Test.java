package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class Dashboard_UserActions_Test extends BaseTest {

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


    @Test(priority = 1, description = "TC01 - Verify About Modal Popup")
    public void verifyUserMenu_AboutInfo() {
        dashboardPage
                .clickAbout();
        dashboardPage
                .verifyDialogContains("OrangeHRM");
        dashboardPage
                .verifyDialogContains("Version");
        dashboardPage
                .closeDialog();
    }

    @Test(priority = 2, description = "TC02 - Verify Logout Functionality")
    public void verifyUserMenu_Logout() {
        dashboardPage
                .clickLogout();
        dashboardPage
                .verifyElementVisible("Submit");
    }

    @Test(priority = 3, description = "TC03 - Verify Hiding and Showing Widgets")
    public void verifyDashboard_Configuration_Toggle() {
        String widgetName = "Quick Launch";

        dashboardPage
                .clickGearIcon()
                .toggleWidgetVisibility(widgetName)
                .clickSave();
        dashboardPage
                .clickGearIcon();
        dashboardPage
                .toggleWidgetVisibility(widgetName)
                .clickSave();
        dashboardPage
                .verifyElementVisible(widgetName);
    }
}