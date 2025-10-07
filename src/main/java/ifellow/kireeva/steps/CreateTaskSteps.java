package ifellow.kireeva.steps;

import ifellow.kireeva.model.CreateTaskModal;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;

public class CreateTaskSteps {
    private final CreateTaskModal createTaskModal = new CreateTaskModal();

    @Когда("пользователь создает новый баг с описанием")
    public void userCreatesNewBugWithDescription() {
        createTaskModal.createTaskBug();
    }

    @И("пользователь переводит задачу в статус {string}")
    public void userChangesTaskStatus(String status) {
        createTaskModal.moveStatus(status);
    }

}
