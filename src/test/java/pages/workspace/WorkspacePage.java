package pages.workspace;

import baseEntities.BasePage;
import com.codeborne.selenide.SelenideElement;
import core.ReadProperties;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WorkspacePage extends BasePage {

    public WorkspacePage(boolean openPageByUrl, String endpoint) {
        super(openPageByUrl, endpoint);
    }

    @Override
    protected void openPage(String endpoint) {

        open(ReadProperties.getInstance().getURL() + endpoint);
    }

    private SelenideElement getCustomFieldsMenuItem() {
        return $("a[title='Custom']");
    }

    public SelenideElement getUpgradeSubscriptionBtn() {
        return $(".btn.btn-primary");
    }

    public SelenideElement getUpgradeSubscriptionHeader() {
        return $(".modal-content .header");
    }

    public WorkspacePage clickCustomFieldsMenuItemUpgrade() {

        getCustomFieldsMenuItem().click();
        return this;
    }
}
