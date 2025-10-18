package ifellow.kireeva.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import ifellow.kireeva.util.CustomProperties;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class WebHook {


    @BeforeAll
    public static void setUpAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(Boolean.getBoolean(CustomProperties.getInstance().getProperty("allure.screenshots")))
                .savePageSource(Boolean.getBoolean(CustomProperties.getInstance().getProperty("allure.savePageSource"))));
    }

    @BeforeAll
    public static void loadConfig() {
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 15_000;
    }

    @BeforeEach
    public void initBrowser() {
        Selenide.open(CustomProperties.getInstance().getProperty("main.url"));
        getWebDriver().manage().window().maximize();
    }

    @AfterEach
    public void afterTest() {
        Selenide.closeWebDriver();
    }
}
