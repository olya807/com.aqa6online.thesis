package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import core.ReadProperties;
import endpoints.UiEndpoints;
import wrappers.Button;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeleteConfirmationPage extends BasePage {

    public DeleteConfirmationPage(boolean openPageByUrl, String endpoint) {
        super(openPageByUrl, endpoint);
    }

    @Override
    protected void openPage(String endpoint) {
        open(ReadProperties.getInstance().getURL() + endpoint);
    }

    public ProjectPage clickDeleteConfirmationButton(String projectCode){
        new Button(" Delete project").click();
        return new ProjectPage(false, String.format(UiEndpoints.PROJECT,projectCode));
    }
}
