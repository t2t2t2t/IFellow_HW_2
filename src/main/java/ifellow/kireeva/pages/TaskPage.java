package ifellow.kireeva.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ifellow.kireeva.util.Util;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class TaskPage {


    private final SelenideElement taskCountElement = $x("//span[contains(text(), '1 из')]").as("Элемент счётчика задач '1 из ...'");
    private final SelenideElement taskButton = $x("//a[@id='create_link']").as("Кнопка 'Создать задачу'");


    public int getTaskCount() {
        return Util.countTask(taskCountElement.getText());
    }

    @Step("Создание задачи")
    public void createNewTask() {
        taskButton.click();
        CreateTaskPage createTaskModal = new CreateTaskPage();
        createTaskModal.createNewTask();

        taskButton.shouldHave(Condition.visible);
        Selenide.refresh();
    }

}
