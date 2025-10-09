package ifellow.kireeva.api;

import ifellow.kireeva.utils.Config;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseAuthApi {

    private static final String URL = Config.getProperty("auth.base_url");
    private static final String REGISTER_URL = URL + "/api/register";
    private static final String LOGIN_URL = URL + "/api/login";
    private static final String LOGOUT_URL = URL + "/api/logout";


    public AuthApi() {
        super(URL);
    }


    public Response register(String jsonBody) {
        return sendPostRequest(REGISTER_URL, jsonBody);
    }

    public Response login(String jsonBody) {
        return sendPostRequest(LOGIN_URL, jsonBody);
    }

    public Response logout(String token) {
        return given()
                .header("Authorization", token)
                .get(LOGOUT_URL);
    }

    private Response sendPostRequest(String url, String body) {
        return given()
                .body(body)
                .post(url);
    }

}
