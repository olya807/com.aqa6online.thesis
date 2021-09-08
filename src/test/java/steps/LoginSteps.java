package steps;

import pages.LoginPage;
import pages.ProjectsPage;

public class LoginSteps {

    public ProjectsPage loginWithCorrectCredentials(String email, String password) {
        new LoginPage(false)
                .setEmail(email)
                .setPassword(password)
                .loginBtnClick();

        return new ProjectsPage(false);
    }
}
