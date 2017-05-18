package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends MasterPage {
    public static By usernameTextBox = By.name("username");
    public static By passwordTextBox = By.name("password");
    public static By loginButton = By.name("LoginButton");

    public static void login(String username, String password) {
        $(usernameTextBox).sendKeys(username);
        $(passwordTextBox).sendKeys(password);
        $(loginButton).click();
    }
}