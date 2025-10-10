package ifellow.kireeva.steps;

import ifellow.kireeva.pages.LoginPage;
import ifellow.kireeva.util.CustomProperties;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;


public class LoginSteps {

    @Дано("пользователь авторизовался успешно в системе")
    @Когда("пользователь авторизовался в EduJira")
    public static void performLogin() {
        open();
        String username = CustomProperties.getInstance().getProperty("username");
        String password = CustomProperties.getInstance().getProperty("password");
        new LoginPage().login(username, password);
    }

    @И("начальная страница прогрузилась")
    public void initialPageLoadedAnd() {
        webdriver().shouldHave(url("https://edujira.ifellow.ru/secure/Dashboard.jspa"));
    }


}
