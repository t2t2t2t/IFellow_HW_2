package ifellow.kireeva.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ifellow.kireeva.client.ReqresClient;
import ifellow.kireeva.context.TestContext;
import ifellow.kireeva.utils.FileUtil;
import io.cucumber.java.ru.Когда;
import io.restassured.response.Response;

import java.util.Map;


public class ReqresStep {

    private final ReqresClient reqresClient = new ReqresClient();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TestContext context;

    private Map<String, String> userData;

    public ReqresStep(TestContext context) {
        this.context = context;
    }

    @Когда("отправляется запрос на создание пользователя с данными из файла {string}")
    public void sendCreateUserRequestWithFile(String fileName) {
        try {
            userData = objectMapper.readValue(
                    FileUtil.readFileFromResources(fileName),
                    new TypeReference<>() {
                    }
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Не удалось прочитать JSON из файла: " + fileName, e);
        }
    }

    @Когда("в запросе устанавливаются имя {string} и должность {string}")
    public void setUserNameAndJob(String name, String job) {
        userData.put("name", name);
        userData.put("job", job);
        Response response = reqresClient.createUser(userData);
        context.setResponse(response);
    }

}
