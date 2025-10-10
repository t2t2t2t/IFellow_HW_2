package ifellow.kireeva.steps;

import ifellow.kireeva.pages.TaskPage;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

public class TaskPageSteps {
    private TaskPage taskPage = new TaskPage();
    private int initialTaskCount;

    @И("проверка общего количество задач в проекте")
    public void checkInitialTaskCount() {
        initialTaskCount = taskPage.getTaskCount();
    }

    @Когда("созданием новый баг Test Bug")
    public void createNewBug() {
        taskPage.createNewTask();
    }

    @Тогда("проверка количество задач увеличилось на 1")
    public void verifyTaskCountIncreasedByOne() {
        int updatedTaskCount = taskPage.getTaskCount();
        Assertions.assertEquals(
                initialTaskCount + 1,
                updatedTaskCount,
                "Количество задач не увеличилось на 1 после создания новой задачи"
        );
    }

}
