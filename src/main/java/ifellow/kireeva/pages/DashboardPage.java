package ifellow.kireeva.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private final SelenideElement projectsButton = $x("//a[@id='browse_link']").as("Кнопка 'Проекты'");
    private final SelenideElement testButton = $x("//a[contains(@id, 'admin_main')]").as("Кнопка 'Test' в меню");
    private final SelenideElement openTasks = $x("//span[contains(text(), 'Открытые задачи')]").as("Элемент 'Открытые задачи'");
    private final SelenideElement tasksButton = $x("//span[@title='Задачи']").as("Кнопка 'Задачи'");

    @Step("Открыть проект Test")
    public boolean isOpenProjectTest() {
        projectsButton.click();
        testButton.click();
        tasksButton.click();
        return openTasks.is(Condition.visible);
    }

}
