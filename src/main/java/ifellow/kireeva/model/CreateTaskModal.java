package ifellow.kireeva.model;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class CreateTaskModal {
    private final SelenideElement fieldTaskType = $x("//input[@id='issuetype-field']");
    private final SelenideElement taskName = $x("//input[@id='summary']");
    private final SelenideElement createButton = $x("//input[@id='create-issue-submit']");
    private final SelenideElement taskButton = $x("//a[@id='create_link']");
    private final SelenideElement fieldLabels = $x("//textarea[@id='labels-textarea']");
    private final SelenideElement statusLabel = $x("//span[@id='status-val']");
    private final SelenideElement createdAlert = $x("//a[contains(@class,'issue-created')]");


    public void setType(String type) {

        fieldTaskType.shouldBe(interactable);
        fieldTaskType.sendKeys(Keys.CONTROL + "a");
        fieldTaskType.sendKeys(Keys.DELETE);

        fieldTaskType.setValue(type);

        SelenideElement firstOption = $$("li[role='option']")
                .shouldBe(sizeGreaterThan(0), Duration.ofSeconds(5))
                .first()
                .shouldBe(visible);

        firstOption.click();

        fieldTaskType.shouldHave(value(type), Duration.ofSeconds(5));
    }


    public void createNewTask() {
        setType("Задача");
        taskName.setValue("Test task");
        createButton.click();
        Selenide.refresh();
    }


    private void fillFieldInVisualMode(String fieldId, String text) {
        if (text == null || text.trim().isEmpty()) return;

        String visualButtonXPath = String.format("//*[@id='%s']//button[text()='Визуальный']", fieldId);
        String iframeXPath = String.format("//*[@id='%s']//iframe", fieldId);

        SelenideElement visualButton = $x(visualButtonXPath).shouldBe(visible, Duration.ofSeconds(5));

        if ("false".equals(visualButton.getAttribute("aria-pressed"))) {
            visualButton.click();
            visualButton.shouldHave(attribute("aria-pressed", "true"), Duration.ofSeconds(5));
        }

        SelenideElement iframe = $x(iframeXPath).shouldBe(visible, Duration.ofSeconds(5));

        switchTo().frame(iframe);

        $(By.tagName("body"))
                .shouldBe(interactable, Duration.ofSeconds(5))
                .sendKeys(text);

        switchTo().defaultContent();
    }

    public void createTaskBug() {
        taskButton.click();

        setType("Ошибка");
        taskName.setValue("Баг репорт");

        fillFieldInVisualMode("description-wiki-edit", "Описание бага");
        fillFieldInVisualMode("environment-wiki-edit", "Описание окружение");

        fieldLabels.setValue("Test");

        createButton.click();
    }

    public void closeTask() {
        createdAlert.shouldBe(visible).click();
        statusButton("Бизнес-процесс").shouldBe(visible).click();
        statusButton("Выполнено").shouldBe(visible).click();
        statusLabel.shouldBe(visible).shouldHave(text("Готово"));
    }

 private SelenideElement statusButton(String status) {
        return $x("//a[./span[text()='" + status + "']]");
    }

}
