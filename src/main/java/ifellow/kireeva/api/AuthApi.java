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
        return given()
                .body(jsonBody)
                .when()
                .post(REGISTER_URL);
    }

    public Response login(String jsonBody) {
        return given()
                .body(jsonBody)
                .when()
                .post(LOGIN_URL);
    }

    public Response logout(String token) {
        return given()
                .header("Authorization", token)
                .when()
                .get(LOGOUT_URL);
    }

}
