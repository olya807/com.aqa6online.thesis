package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import core.ReadProperties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage {

    public LoginPage(boolean openPageByUrl) {
        super(openPageByUrl);
    }

    @Override
    protected void openPage() {
        open(ReadProperties.getInstance().getURL() + endpoint);
    }

    public final static String endpoint = "login";

    private SelenideElement getEmailField() {
        return $("#inputEmail");
    }

    private SelenideElement getPasswordField() {
        return $("#inputPassword");
    }

    private SelenideElement getLoginButton() {
        return $("#btnLogin");
    }

    private SelenideElement getCredentialsDoNotMatchMessage() {
        return $(".form-control-feedback");
    }

    // Атомарные методы
    public LoginPage setEmail(String email) {
        getEmailField().sendKeys(email);
        return this;
    }

    public LoginPage setPassword(String password) {
        getPasswordField().sendKeys(password);
        return this;
    }

    public LoginPage loginBtnClick() {
        getLoginButton().click();
        return this;
    }

    public String credentialsDoNotMatch() {
        return getCredentialsDoNotMatchMessage().getText();
    }

    public ProjectsPage successLoginBtnClick() {
        loginBtnClick();
        return new ProjectsPage(false);
    }
}
