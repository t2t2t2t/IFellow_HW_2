package ifellow.kireeva.steps;

import ifellow.kireeva.context.TestContext;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonStep {

    private final TestContext context;

    public CommonStep(TestContext context) {
        this.context = context;
    }

    @Тогда("статус ответа должен быть {int}")
    public void checkStatusCode(int expectedStatus) {
        Response response = context.getResponse();
        assertEquals(expectedStatus, response.getStatusCode(),
                "Ожидался статус " + expectedStatus);
    }

    @Тогда("в ответе поле {string} должно быть {string}")
    public void checkResponseField(String field, String expectedValue) {
        Response response = context.getResponse();
        String actualValue = response.jsonPath().getString(field);
        assertEquals(expectedValue, actualValue,
                "Поле '" + field + "' должно быть '" + expectedValue + "'");
    }

    @Тогда("в теле ответа должно содержаться {string}")
    public void responseBodyShouldContain(String text) {
        Response response = context.getResponse();
        assertTrue(response.body().asString().contains(text));
    }
}
