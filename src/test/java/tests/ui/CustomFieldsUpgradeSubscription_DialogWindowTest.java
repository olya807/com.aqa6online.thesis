package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import core.ReadProperties;
import endpoints.UiEndpoints;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UpgradeSubscriptionPage;
import pages.workspace.MembersPage;

public class CustomFieldsUpgradeSubscription_DialogWindowTest extends BaseTest {

    UpgradeSubscriptionPage upgradeSubscriptionPage;

    @Link("https://example.com/")
    @Test(description = "Dialog window check")
    @Description("Click 'CustomFields' menu and check 'Upgrade your subscription' dialog window")
    public void checkCreateProjectAlert() {

        String upgradeText = "Upgrade your subscription";

        new LoginPage(true, UiEndpoints.LOGIN)
                .setEmail(ReadProperties.getInstance().getUsername())
                .setPassword(ReadProperties.getInstance().getPassword())
                .successLoginBtnClick();

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
