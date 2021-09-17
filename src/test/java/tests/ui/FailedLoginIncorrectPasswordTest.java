package tests.ui;

import baseEntities.BaseTest;
import com.codeborne.selenide.Condition;
import core.ReadProperties;
import io.qameta.allure.Description;
import models.ui.TestCase;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import steps.CreateCaseStep;
import steps.CreateProjectStep;
import steps.LoginStep;

public class FailedLoginIncorrectPasswordTest extends BaseTest {

    @Test
    @Description("Failed Test: Login with incorrect 'Password' field value")
    public void failedLoginIncorrectPasswordValueTest() {

        String loginErrorMessage = "These credentials do not match our records.";
        String incorrectPSW = RandomStringUtils.randomAlphanumeric(8);

        new LoginStep()
                .incorrectLogin(ReadProperties.getInstance().getUsername(), incorrectPSW)
                .getCredentialsDoNotMatchMessage()
                .shouldBe(Condition.visible)
                .shouldHave(Condition.exactText("Lorem Ipsum"));
    }
}
