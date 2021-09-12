package baseEntities;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import core.ReadProperties;
import io.qameta.allure.selenide.AllureSelenide;
import models.ui.TestCase;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeSuite
    public static void setupAllureReports() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );

        //setup logger
        org.apache.log4j.BasicConfigurator.configure();
    }

    @BeforeTest
    public void setup() {

        Configuration.baseUrl = ReadProperties.getInstance().getURL();
        Configuration.browser = ReadProperties.getInstance().getBrowserName();
        Configuration.startMaximized = true;
        Configuration.headless = false;
        Configuration.pageLoadTimeout = 15000;
        //Configuration.fastSetValue = false;
    }
}
