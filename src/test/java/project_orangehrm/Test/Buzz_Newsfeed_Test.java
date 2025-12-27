package project_orangehrm.Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project_orangehrm.Base.BaseTest;
import project_orangehrm.Pages.BuzzPage;
import project_orangehrm.Pages.DashboardPage;
import project_orangehrm.Pages.LoginPage;

public class Buzz_Newsfeed_Test extends BaseTest {

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

    @Test(priority = 1, description = "TC01 - Positive: Verify Post Lifecycle (Create, View, Delete)")
    public void verifyPost_Lifecycle_Success() {
        String postText = "Auto Post ";
        buzzPage
                .createPost(postText)
                .verifySuccessMessage()
                .verifyBodyContains(postText)
                .clickPostOptions(postText)
                .clickDeleteOption()
                .confirmDelete()
                .verifySuccessMessage();
    }

    @Test(priority = 2, description = "TC02 - Positive: Verify Engagement (Like and Comment)")
    public void verifyEngagement_Success() {
        String postText = "Engagement ";
        String comment = "Nice Update!";
        buzzPage
                .createPost(postText)
                .clickLikeOnPost(postText)
                .addCommentOnPost(postText, comment)
                .verifyBodyContains(comment);
    }

    @Test(priority = 3, description = "TC03 - Positive: Verify Edit Post")
    public void verifyPost_Edit_Success() {
        String original = "Original Text";
        String updated = "Updated Text";

        buzzPage
                .createPost(original)
                .clickPostOptions(original)
                .clickEditOption()
                .editPostContent(updated)
                .verifySuccessMessage()
                .verifyBodyContains(updated);
    }

    @Test(priority = 4, description = "TC04 - Negative: Verify Post Creation with Empty Content")
    public void verifyEmptyPost_Error() {
        buzzPage
                .createPost("")
                .verifyEmptyPostError("Required");
    }

    @Test(priority = 5, description = "TC05 - Negative: Verify Cancellation of Post Deletion")
    public void verifyCancelDeletion_Flow() {
        String postText = "Safety Check ";
        buzzPage
                .createPost(postText)
                .clickPostOptions(postText)
                .clickDeleteOption()
                .cancelDelete()
                .verifyBodyContains(postText);
    }
}