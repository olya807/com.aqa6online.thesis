package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import core.ReadProperties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProjectsPage extends BasePage {

    public ProjectsPage(boolean openPageByUrl) {
        super(openPageByUrl);
    }

    @Override
    protected void openPage() {
        open(ReadProperties.getInstance().getURL() + endpoint);
    }

    public final static String endpoint = "projects";

    public SelenideElement getUserMenuIcon() {

        return $("#user-menu");
    }

    private SelenideElement getCreateProjectButton() {

        return $("#createButton");
    }

    public ProjectCreatePage createProjectButtonClick() {

        getCreateProjectButton().click();
        return new ProjectCreatePage(false);
    }
}
