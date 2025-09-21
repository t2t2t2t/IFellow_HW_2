package ifellow.kireeva;

import com.codeborne.selenide.Selenide;
import ifellow.kireeva.model.CreateTaskModal;
import ifellow.kireeva.pages.*;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FullScenarioTest extends WebHook {

    @Test(description = "Полный сценарий: авторизация, проверка задач, создание и закрытие бага")
    public void testFullScenario() {

        performLogin();

        navigateToTestProject();

        verifyTaskCountIncrease();

        verifySeleniumTaskDetails();

        createAndCloseBug();

        //Selenide.sleep(60_000);
    }


    public void performLogin() {
        LoginHelper.performLogin();
        webdriver().shouldHave(url("https://edujira.ifellow.ru/secure/Dashboard.jspa"));
    }


    public void navigateToTestProject() {
        DashboardPage dashboardPage = new DashboardPage();
        assertTrue(dashboardPage.isOpenProjectTest(), "'Открытые задачи' не видно на странице");
    }

    public void verifyTaskCountIncrease() {
        TaskPage taskPage = new TaskPage();
        int initialTaskCount = taskPage.getTaskCount();

        taskPage.createNewTask();

        int updatedTaskCount = taskPage.getTaskCount();
        assertEquals(initialTaskCount + 1, updatedTaskCount, "Количество задач не увеличилось на 1 после создания" +
                " новой задачи");
    }


    public void verifySeleniumTaskDetails() {
        ProjectPage taskPage = new ProjectPage();
        taskPage.searchTask("TestSeleniumATHomework");
        assertTrue(taskPage.isTaskStatusCorrect(), "Статус задачи не содержит 'Сделать'");
        assertTrue(taskPage.isFixVersionCorrect(), "'Исправить в версиях' не содержит 'Version 2.0'");
    }


    private void createAndCloseBug() {
        CreateTaskModal createTaskModal = new CreateTaskModal();
        createTaskModal.createTaskBug();
        createTaskModal.closeTask();

    }
}

