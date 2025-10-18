package ifellow.kireeva.tests;

import ifellow.kireeva.hooks.WebHook;
import ifellow.kireeva.pages.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FullScenarioTest extends WebHook {

    @Test
    @DisplayName("Авторизоваться")
    public void testLogin() {
        performLogin();
        webdriver().shouldHave(url("https://edujira.ifellow.ru/secure/Dashboard.jspa"));
    }


    @Test
    @DisplayName("Переход в проект 'Test'")
    public void testNavigateToTestProject() {
        performLogin();
        navigateToTestProject();
    }

    @Test
    @DisplayName("Проверка количества задач")
    public void testTaskCountIncreasesAfterCreation() {
        performLogin();
        navigateToTestProject();
        verifyTaskCountIncrease();
    }

    @Test
    @DisplayName("Проверка задачи TestSeleniumATHomework")
    public void testSeleniumTaskDetails() {
        performLogin();
        navigateToTestProject();
        verifySeleniumTaskDetails();
    }


    @Test
    @DisplayName("Создание и закрытие бага")
    public void testCreateAndCloseBug() {
        performLogin();
        navigateToTestProject();
        createAndCloseBug();
    }


    public void performLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();
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
        CreateTaskPage createTaskModal = new CreateTaskPage();
        createTaskModal.createTaskBug();
        createTaskModal.closeTask();

    }
}

