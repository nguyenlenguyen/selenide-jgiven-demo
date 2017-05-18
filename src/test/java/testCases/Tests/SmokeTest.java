package testCases.Tests;

import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MasterPage;

public class SmokeTest extends BaseTest {
    @Test
    public void VerifyLogin() {
        MasterPage.navigateToTestSite();
        LoginPage.login("vpd@gmail.com", "password");
        MasterPage.verifyUserLoggedIn("vpd@gmail.com");
    }
}