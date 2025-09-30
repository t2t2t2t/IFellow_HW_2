package ifellow.kireeva.api;

import ifellow.kireeva.utils.Config;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ReqresApi extends BaseApi {
    private static final String URL = Config.getProperty("req_res.base_url");
    private static final String USERS_URL = URL + "/api/users";


    public ReqresApi() {
        super(URL);
    }

    public ValidatableResponse postUser(Map<String, String> data) {
        return given()
                .when()
                .header("x-api-key", "reqres-free-v1")
                .body(data)
                .post(USERS_URL)
                .then();
    }

}
