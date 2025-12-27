package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Test(priority = 1, description = "TC01 - Positive: Verify successful login with valid credentials")
    public void verifyLogin_ValidCredentials_Success() {
        loginPage
                .enterUsername("Admin")
                .enterPassword("admin123")
                .clickLogin();
        dashboardPage
                .verifyDashboard("Dashboard");
    }

    @Test(priority = 2, description = "TC02 - Negative: Verify error message when logging in with invalid credentials")
    public void verifyLogin_InvalidCredentials_ShowError() {
        loginPage
                .enterUsername("Admin")
                .enterPassword("WrongPass")
                .clickLogin()
                .verifyInvalidCredentialsError("Invalid credentials");
    }

    @Test(priority = 3, description = "TC03 - Negative: Verify error message when required fields are empty")
    public void verifyLogin_EmptyFields_ShowRequiredMessage() {
        loginPage
                .enterUsername("")
                .enterPassword("")
                .clickLogin()
                .verifyRequiredFieldError("Required");
    }
}