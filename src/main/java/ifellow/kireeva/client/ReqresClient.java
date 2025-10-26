package ifellow.kireeva.client;

import ifellow.kireeva.api.ReqresApi;
import io.restassured.response.Response;

import java.util.Map;

public class ReqresClient {

    private final ReqresApi reqresApi = new ReqresApi();

    public Response createUser(Map<String, String> data) {
        return reqresApi.postUser(data)
                .extract()
                .response();
    }
}
