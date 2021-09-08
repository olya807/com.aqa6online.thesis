package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import core.ReadProperties;
import endpoints.UiEndpoints;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.workspace.WorkspacePage;

public class CustomFieldsUpgradeSubscription_DialogWindowTest extends BaseTest {

    WorkspacePage workspacePage;

    @Test
    @Description("Click 'CustomFields' menu and check 'Upgrade your subscription' dialog window")
    public void checkCreateProjectAlert() {

        String upgradeText = "Upgrade your subscription";

        new LoginPage(true, UiEndpoints.LOGIN)
                .setEmail(ReadProperties.getInstance().getUsername())
                .setPassword(ReadProperties.getInstance().getPassword())
                .successLoginBtnClick();

        workspacePage = new WorkspacePage(true, UiEndpoints.WORKSPACE)
                .clickCustomFieldsMenuItemUpgrade();

        workspacePage
                .getUpgradeSubscriptionHeader()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.matchText(upgradeText));
        workspacePage.getUpgradeSubscriptionBtn()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText(upgradeText));
    }
}
