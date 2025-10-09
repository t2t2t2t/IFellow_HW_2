package ifellow.kireeva;

import com.fasterxml.jackson.databind.ObjectMapper;
import ifellow.kireeva.dto.auth.User;
import ifellow.kireeva.steps.AuthSteps;
import ifellow.kireeva.utils.FileUtil;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AuthTest {

    Logger log = Logger.getLogger(AuthTest.class.getName());

    private static AuthSteps authSteps;
    private static User user;
    private static String authToken;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setUp() throws Exception {
        authSteps = new AuthSteps();

        String jsonContent = FileUtil.readFileFromResources("auth.json");
        user = objectMapper.readValue(jsonContent, User.class);
    }

    @Test
    void testRegistration() {
        Response response = authSteps.registerUser(
                FileUtil.readFileFromResources("auth.json")
        );

        assertEquals(200, response.getStatusCode(), "Должен быть статус 200");
        log.info("Тест регистрации пройден");
    }

    @Test
    void testLoginUserNotFound() throws Exception {

        User wrongUser = new User(
                "не пользователь",
                user.getPassword()
        );

        Response response = authSteps.loginUser(objectMapper.writeValueAsString(wrongUser));

        assertEquals(401, response.getStatusCode(), "Должен быть статус 401");
        assertTrue(response.getBody().asString().contains("not found"));
        log.info("Тест пройден, Пользователь не найден: " + response.getBody().asString());

    }

    @Test
    void testLoginWrongPassword() throws Exception {

        User wrongPass = new User(
                user.getUsername(),
                "не пароль"
        );

        Response response = authSteps.loginUser(objectMapper.writeValueAsString(wrongPass));

        assertEquals(401, response.getStatusCode(), "Должен быть статус 401");
        assertTrue(response.getBody().asString().contains("not right pass"));
        log.info("Тест пройден, Неверный пароль: " + response.getBody().asString());
    }

    @Test
    void testLoginSuccess() {

        Response responseRegister = authSteps.registerUser(
                FileUtil.readFileFromResources("auth.json")
        );

        assertEquals(200, responseRegister.getStatusCode(), "Должен быть статус 200");
        log.info("Тест регистрации пройден");

        Response responseLogin = authSteps.loginUser(
                FileUtil.readFileFromResources("auth.json")
        );

        assertEquals(200, responseLogin.getStatusCode(), "Должен быть статус 200");

        assertTrue(responseLogin.getBody().asString().contains("token"));

        authToken = authSteps.extractTokenFromResponse(responseLogin);

        log.info("Тест пройден, Токен получен: " + authToken);
    }

    @Test
    void testLogoutFailure() {
        Response response = authSteps.logoutUser("12345678-1234-1234-1234-123456789012");

        assertEquals(401, response.getStatusCode(), "Должен быть статус 401");
        assertTrue(response.getBody().asString().contains("not found"));
        log.info("Тест пройден, Выход неуспешен: " + response.getBody().asString());
    }

    @Test
    void testLogoutSuccess() {

        Response responseRegister = authSteps.registerUser(
                FileUtil.readFileFromResources("auth.json")
        );

        assertEquals(200, responseRegister.getStatusCode(), "Должен быть статус 200");
        log.info("Тест регистрации пройден");

        Response responseLogin = authSteps.loginUser(
                FileUtil.readFileFromResources("auth.json")
        );

        assertEquals(200, responseLogin.getStatusCode(), "Должен быть статус 200");

        assertTrue(responseLogin.getBody().asString().contains("token"));

        authToken = authSteps.extractTokenFromResponse(responseLogin);

        log.info("Тест пройден, Токен получен: " + authToken);


        Response responseLogout = authSteps.logoutUser(authToken);

        assertEquals(200, responseLogout.getStatusCode(), "Должен быть статус 200");
        assertTrue(responseLogout.getBody().asString().contains("success logout"));
        log.info("Тест пройден, Выход успешен: " + responseLogout.getBody().asString());
    }
}
