package pages;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import core.ReadProperties;
import endpoints.UiEndpoints;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CasePage extends BasePage {

    public CasePage(boolean openPageByUrl, String endpoint) {
        super(openPageByUrl, endpoint);
    }

    @Override
    protected void openPage(String endpoint) {
        open(ReadProperties.getInstance().getURL() + endpoint);
    }

    public SelenideElement getFileUploadField() {
        return $("#file");
    }

    private SelenideElement getSubmitBtn() {
        return $("[type='submit']");
    }

    public String getFileUploadFieldValue() {

        return getFileUploadField().getValue();
    }

    public ProjectPage submitBtnClick() {

        getSubmitBtn().click();
        return new ProjectPage(false, UiEndpoints.PROJECT);
    }
}
