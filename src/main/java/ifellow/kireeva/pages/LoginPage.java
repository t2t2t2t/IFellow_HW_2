package ifellow.kireeva.pages;

import com.codeborne.selenide.SelenideElement;
import ifellow.kireeva.util.CustomProperties;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement usernameField = $x("//input[@id='login-form-username']").as("Поле 'Имя пользователя'");
    private final SelenideElement passwordField = $x("//input[@id='login-form-password']").as("Поле 'Пароль'");
    private final SelenideElement loginButton = $x("//input[@id='login']").as("Кнопка 'Войти'");

    @Step("Авторизация пользователя {username}")
    public void login() {
        String username = CustomProperties.getInstance().getProperty("username");
        String password = CustomProperties.getInstance().getProperty("password");
        Allure.parameter("username", username);
        Allure.parameter("password", password, Parameter.Mode.MASKED);
        usernameField.shouldBe(visible).setValue(username);
        setPasswordField(passwordField, password);
        loginButton.shouldBe(enabled).click();
    }

    private void setPasswordField(SelenideElement field, String value) {
        field.toWebElement().sendKeys(value);
    }
}
