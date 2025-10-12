package ifellow.kireeva.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement usernameField = $x("//input[@id='login-form-username']").as("Поле 'Имя пользователя'");
    private final SelenideElement passwordField = $x("//input[@id='login-form-password']").as("Поле 'Пароль'");
    private final SelenideElement loginButton = $x("//input[@id='login']").as("Кнопка 'Войти'");

    @Step("Вход в систему")
    public void login (String username, String password) {
        usernameField.shouldBe(visible).setValue(username);
        passwordField.shouldBe(visible).setValue(password);
        loginButton.shouldBe(enabled).click();
    }
}
