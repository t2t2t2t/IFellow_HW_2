package ifellow.kireeva;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ifellow.kireeva.steps.ReqresSteps;
import ifellow.kireeva.utils.Config;
import ifellow.kireeva.utils.FileUtil;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresTest {

    @Test
    @DisplayName("Create user and check response")
    void createUserAndValidateResponse() {
        Map<String, String> data;
        try {
            data = new ObjectMapper().readValue(
                    FileUtil.readFileFromResources("user.json"), HashMap.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        data.put("name", "Tomato");
        data.put("job", "Eat maket");

        ReqresSteps reqResSteps = new ReqresSteps();
        Response response = reqResSteps.createUser(data);

        Map<String, String> responseData = response.jsonPath().getMap("");

        int expectedStatusCode = Integer.parseInt(Config.getProperty("req_res.expected_status_code"));
        String expectedName = Config.getProperty("req_res.name");
        String expectedJob = Config.getProperty("req_res.job");

        assertEquals(expectedStatusCode, response.getStatusCode(),
                "Код ответа должен быть " + expectedStatusCode);
        assertEquals(expectedName, responseData.get("name"),
                "Имя в ответе должно быть: " + expectedName);
        assertEquals(expectedJob, responseData.get("job"),
                "Должность в ответе должна быть: " + expectedJob);
    }
}
