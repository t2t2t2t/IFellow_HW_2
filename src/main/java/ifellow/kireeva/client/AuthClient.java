package ifellow.kireeva.client;

import ifellow.kireeva.api.AuthApi;
import io.restassured.response.Response;

public class AuthClient {
    private final AuthApi authApi = new AuthApi();

    public Response registerUser(String jsonBody) {
        return authApi.register(jsonBody);
    }

    public Response loginUser(String jsonBody) {
        return authApi.login(jsonBody);
    }

    public Response logoutUser(String token) {
        return authApi.logout(token);
    }

    public String extractTokenFromResponse(Response response) {
        String responseBody = response.getBody().asString();
        if (responseBody.contains("token :")) {
            return responseBody.split("token :")[1].trim();
        }
        throw new RuntimeException("Токен не найден в ответе: " + responseBody);
    }
}
