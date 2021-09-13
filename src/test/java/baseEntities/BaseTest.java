package baseEntities;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import core.ReadProperties;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;

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

    @AfterClass
    public void tearDown() {

    }
}
