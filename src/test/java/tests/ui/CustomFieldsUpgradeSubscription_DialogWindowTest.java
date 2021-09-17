package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import endpoints.UiEndpoints;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import pages.UpgradeSubscriptionPage;
import pages.workspace.MembersPage;
import steps.LoginStep;

public class CustomFieldsUpgradeSubscription_DialogWindowTest extends BaseTest {

    UpgradeSubscriptionPage upgradeSubscriptionPage;

    @Test
    @Description("Click 'CustomFields' menu and check 'Upgrade your subscription' dialog window")
    public void checkCreateProjectAlert() {

        String upgradeText = "Upgrade your subscription";

        new LoginStep()
                .correctLogin();

        upgradeSubscriptionPage = new MembersPage(true, UiEndpoints.WORKSPACE_MEMBERS)
                .clickCustomFieldsMenuItemUpgrade();

        upgradeSubscriptionPage
                .getUpgradeSubscriptionHeader()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.matchText(upgradeText));

        upgradeSubscriptionPage
                .getUpgradeSubscriptionBtn()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText(upgradeText));
    }
}
