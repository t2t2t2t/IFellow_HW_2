package ifellow.kireeva;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import ifellow.kireeva.util.CustomProperties;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.PageLoadStrategy;

public class WebHook {


    @Before
    public void initBrowser() {

        Configuration.pageLoadStrategy = PageLoadStrategy.EAGER.toString();
        Configuration.timeout = 30000;
        Configuration.pageLoadTimeout = 60000;

        Selenide.open(CustomProperties.getInstance().getProperty("main.url"));

        Selenide.webdriver().driver().getWebDriver().manage().window().maximize();
    }

    @After
    public void afterTest() {
        Selenide.closeWebDriver();
    }


}
