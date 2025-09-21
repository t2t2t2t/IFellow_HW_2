package ifellow.kireeva;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import ifellow.kireeva.util.CustomProperties;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.PageLoadStrategy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class WebHook {

    @BeforeSuite
    public static void setUpAllure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @BeforeSuite(dependsOnMethods = "setUpAllure")
    public static void loadConfig() {
        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 15000;

    }

    @BeforeMethod
    public void initBrowser() {
        System.out.println(CustomProperties.getInstance().getProperty("main.url"));
        Selenide.open(CustomProperties.getInstance().getProperty("main.url"));
        getWebDriver().manage().window().maximize();
    }

    @AfterMethod
    public void afterTest() {
         Selenide.closeWebDriver();
    }
}
