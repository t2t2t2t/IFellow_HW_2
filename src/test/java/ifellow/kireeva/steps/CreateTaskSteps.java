package ifellow.kireeva.steps;

import ifellow.kireeva.pages.CreateTaskPage;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;

public class CreateTaskSteps {
    private final CreateTaskPage createTaskPage = new CreateTaskPage();

    @Когда("пользователь создает новый баг с описанием")
    public void userCreatesNewBugWithDescription() {
        createTaskPage.createTaskBug();
    }

    @И("пользователь переводит задачу в статус {string}")
    public void userChangesTaskStatus(String status) {
        createTaskPage.moveStatus(status);
    }

}
