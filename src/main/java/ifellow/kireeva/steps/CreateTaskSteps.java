package ifellow.kireeva.steps;

import ifellow.kireeva.model.CreateTaskModal;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;

public class CreateTaskSteps {
    private final CreateTaskModal createTaskModal = new CreateTaskModal();

    @И("пользователь создает новый баг с описанием")
    public void userCreatesNewBugWithDescription() {
        createTaskModal.createTaskBug();
    }

    @Когда("пользователь переводит задачу в статус {string}")
    public void userChangesTaskStatus(String status) {
        createTaskModal.moveStatus(status);
    }

}
