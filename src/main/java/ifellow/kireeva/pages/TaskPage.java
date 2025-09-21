package ifellow.kireeva.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ifellow.kireeva.model.CreateTaskModal;
import ifellow.kireeva.util.Util;

import static com.codeborne.selenide.Selenide.$x;

public class TaskPage {
    private final SelenideElement taskCountElement = $x("//span[contains(text(), '1 из')]");
    private final SelenideElement taskButton = $x("//a[@id='create_link']");


    public int getTaskCount() {
        return Util.countTask(taskCountElement.getText());
    }


    public void createNewTask() {
        taskButton.click();
        CreateTaskModal createTaskModal = new CreateTaskModal();
        createTaskModal.createNewTask();

        taskButton.shouldHave(Condition.visible);
        Selenide.refresh();
    }

}
