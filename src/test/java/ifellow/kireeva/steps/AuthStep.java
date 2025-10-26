package ifellow.kireeva.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import ifellow.kireeva.client.AuthClient;
import ifellow.kireeva.context.TestContext;
import ifellow.kireeva.dto.auth.User;
import ifellow.kireeva.utils.FileUtil;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Epic;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Epic("")
public class AuthStep {

    private final AuthClient authClient = new AuthClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final TestContext context;
    private String authToken;

    public AuthStep(TestContext context) {
        this.context = context;
    }


    @Когда("пользователь регистрируется с данными из файла {string}")
    public void registerWithFile(String fileName) {

        String json = FileUtil.readFileFromResources(fileName);
        Response response = authClient.registerUser(json);
        context.setResponse(response);
    }

    @Дано("пользователь пытается войти с логином {string} и паролем из {string}")
    public void loginWithCustomUsernameAndBasePassword(String username, String fileName) throws Exception {
        String json = FileUtil.readFileFromResources(fileName);
        User base = objectMapper.readValue(json, User.class);
        User custom = new User(username, base.getPassword());
        Response response = authClient.loginUser(objectMapper.writeValueAsString(custom));
        context.setResponse(response);
    }

    @Дано("пользователь пытается войти с верным логином, но паролем {string}")
    public void loginWithCorrectUsernameAndWrongPassword(String wrongPassword) throws Exception {
        String json = FileUtil.readFileFromResources("auth.json");
        User baseUser = objectMapper.readValue(json, User.class);
        User user = new User(baseUser.getUsername(), wrongPassword);
        Response response = authClient.loginUser(objectMapper.writeValueAsString(user));
        context.setResponse(response);
    }

    @Когда("пользователь входит в систему с теми же данными")
    public void loginWithSameData() {
        String json = FileUtil.readFileFromResources("auth.json");
        Response response = authClient.loginUser(json);
        context.setResponse(response);
    }

    @Когда("извлекается токен авторизации")
    public void extractAuthToken() {
        Response response = context.getResponse();
        assertTrue(response.body().asString().contains("token"));
        authToken = authClient.extractTokenFromResponse(response);
    }

    @Когда("пользователь пытается выйти из системы с токеном {string}")
    public void logoutWithToken(String token) {
        Response response = authClient.logoutUser(token);
        context.setResponse(response);
    }

    @Когда("пользователь выходит из системы с этим токеном")
    public void logoutWithExtractedToken() {
        Response response = authClient.logoutUser(authToken);
        context.setResponse(response);
    }

    @Тогда("в ответе должен присутствовать токен")
    public void tokenShouldBePresent() {
        Response response = context.getResponse();
        assertTrue(response.body().asString().contains("token"));
        authToken = authClient.extractTokenFromResponse(response);
    }
}