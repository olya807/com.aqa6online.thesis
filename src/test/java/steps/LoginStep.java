package steps;

import core.ReadProperties;
import endpoints.UiEndpoints;
import pages.LoginPage;
import pages.ProjectCreatePage;
import pages.ProjectsPage;

public class LoginStep {

    public ProjectsPage correctLogin() {

        new LoginPage(true, UiEndpoints.LOGIN)
                .setEmail(ReadProperties.getInstance().getUsername())
                .setPassword(ReadProperties.getInstance().getPassword())
                .successLoginBtnClick();
        return new ProjectsPage(false, UiEndpoints.PROJECTS);
    }

    public LoginPage incorrectLogin(String incorrectLogin, String incorrectPsw) {

        new LoginPage(true, UiEndpoints.LOGIN)
                .setEmail(incorrectLogin)
                .setPassword(incorrectPsw)
                .successLoginBtnClick();
        return new LoginPage(false, UiEndpoints.LOGIN);
    }
}
