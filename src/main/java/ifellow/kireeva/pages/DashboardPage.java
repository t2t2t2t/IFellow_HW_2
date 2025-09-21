package ifellow.kireeva.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage {
    private final SelenideElement projectsButton = $x("//a[@id='browse_link']");
    private final SelenideElement testButton = $x("//a[contains(@id, 'admin_main')]");
    private final SelenideElement openTasks = $x("//span[contains(text(), 'Открытые задачи')]");
    private final SelenideElement tasksButton = $x("//span[@title='Задачи']");

    public boolean isOpenProjectTest() {
        projectsButton.click();
        testButton.click();
        tasksButton.click();
        return openTasks.is(Condition.visible);
    }

}
