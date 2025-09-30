package ifellow.kireeva.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseApi {

    public BaseApi(String baseUrl) {
        RestAssured.requestSpecification = baseRequestSpec(baseUrl);
        RestAssured.responseSpecification = baseResponseSpec();
    }

    public static RequestSpecification baseRequestSpec(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification baseResponseSpec() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .build();
    }
}
