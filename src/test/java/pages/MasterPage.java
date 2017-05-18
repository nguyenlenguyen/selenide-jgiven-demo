package pages;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MasterPage {
    public static By usernameLabel = By.id("ctl00_lblNameLabel");

    public static void navigateToTestSite() {
        ChromeDriverManager.getInstance().setup();
        open("https://dv2archcc.absolute.com");

    }

    public static void verifyUserLoggedIn(String username) {
        $(usernameLabel).shouldHave(text(username));
    }
}