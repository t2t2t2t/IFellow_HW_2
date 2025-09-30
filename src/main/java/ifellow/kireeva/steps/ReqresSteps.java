package ifellow.kireeva.steps;

import ifellow.kireeva.api.ReqresApi;
import io.restassured.response.Response;

import java.util.Map;

public class ReqresSteps {

    private final ReqresApi reqresApi = new ReqresApi();

    public Response createUser(Map<String, String> data) {
        return reqresApi.postUser(data)
                .extract()
                .response();
    }
}
