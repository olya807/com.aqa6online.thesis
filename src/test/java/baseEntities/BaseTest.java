package baseEntities;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import core.ReadProperties;
import io.qameta.allure.selenide.AllureSelenide;
import models.ui.TestCase;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;

public class BaseTest {
    protected String randomTestCaseName = RandomStringUtils.randomAlphanumeric(15);
    protected String updatedPreconds = RandomStringUtils.randomAlphanumeric(15);
    protected TestCase testCase;
    protected TestCase testCase2;

    @BeforeSuite
    public static void setupAllureReports() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );

        //setup logger
        org.apache.log4j.BasicConfigurator.configure();
    }

    @BeforeSuite
    public void prepareData() {

        testCase = TestCase.builder()
                .title(randomTestCaseName)
                .status("Actual")
                .description(RandomStringUtils.randomAlphabetic(10))
                .suite("Test cases without suite")
                .severity("Critical")
                .priority("High")
                .type("Smoke")
                .layer("Unit")
                .isFlaky("No")
                .milestone("Not set")
                .behavior("Not set")
                .automationStatus("Not automated")
                .preconditions(RandomStringUtils.randomAlphabetic(50))
                .postconditions(RandomStringUtils.randomAlphanumeric(50))
                .build();

        testCase2 = TestCase.builder()
                .layer("API")
                .preconditions(updatedPreconds)
                .build();
    }

    @BeforeClass
    public void setup() {

        clearBrowserCookies();
        Configuration.baseUrl = ReadProperties.getInstance().getURL();
        Configuration.browser = ReadProperties.getInstance().getBrowserName();
        Configuration.startMaximized = true;
        Configuration.headless = false;
        Configuration.pageLoadTimeout = 15000;
        //Configuration.fastSetValue = false;
    }
}
