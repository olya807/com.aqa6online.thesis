package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import core.ReadProperties;
import endpoints.UiEndpoints;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProjectsPage extends BasePage {

    public ProjectsPage(boolean openPageByUrl, String endpoint) {
        super(openPageByUrl, endpoint);
    }

    @Override
    protected void openPage(String endpoint) {
        open(ReadProperties.getInstance().getURL() + endpoint);
    }

    public SelenideElement getUserMenuIcon() {

        return $("#user-menu");
    }

    private SelenideElement getCreateProjectButton() {

        return $("#createButton");
    }

    public ProjectCreatePage createProjectButtonClick() {

        getCreateProjectButton().click();
        return new ProjectCreatePage(false, UiEndpoints.CREATE_PROJECT);
    }
}
