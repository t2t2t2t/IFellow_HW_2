package ifellow.kireeva.steps;

import ifellow.kireeva.pages.ProjectPage;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;

public class ProjectPageSteps {

    private final ProjectPage projectPage = new ProjectPage();

    @Когда("пользователь ищет задачу {string}")
    public void userSearchesTask(String taskName) {
        projectPage.searchTask(taskName);
        System.out.println("Поиск задачи: " + taskName);
    }

    @И("пользователь переходит в задачу")
    public void userOpensTask() {
        System.out.println("Переход в задачу");
    }

    @Тогда("статус задачи равен {string}")
    public void taskStatusEquals(String expectedStatus) {
        boolean isStatusCorrect = projectPage.isTaskStatusCorrect();
        Assertions.assertTrue(isStatusCorrect,
                "Статус задачи не содержит '" + expectedStatus + "'");
        System.out.println("Проверка статуса: " + expectedStatus);
    }

    @И("версия задачи равна {string}")
    public void taskVersionEquals(String expectedVersion) {
        boolean isVersionCorrect = projectPage.isFixVersionCorrect();
        Assertions.assertTrue(isVersionCorrect,
                "'Исправить в версиях' не содержит '" + expectedVersion + "'");
        System.out.println("Проверка версии: " + expectedVersion);
    }
}
