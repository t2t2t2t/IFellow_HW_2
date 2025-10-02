package ifellow.kireeva.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseAuthApi {


    public BaseAuthApi(String baseUrl) {
        RestAssured.requestSpecification = baseRequestSpec(baseUrl);
        RestAssured.responseSpecification = null;
    }

    public static RequestSpecification baseRequestSpec(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }
}
