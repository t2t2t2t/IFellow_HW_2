package ifellow.kireeva.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage {

private final SelenideElement searchField = $x("//input[@id='quickSearchInput']");
    private final ElementsCollection searchElements = $$x("//li[contains(@class,'quick-search-result-item')]");
    private final SelenideElement taskStatus = $x("//span[contains(text(),'Сделать')]");
    private final SelenideElement fixVersion = $x("//a[contains(text(), 'Version 2.0')]");



    public void searchTask(String taskName) {
        searchField.setValue(taskName);
        searchElements.shouldHave(sizeGreaterThan(2)).findBy(text(taskName)).click();
    }


    public boolean isTaskStatusCorrect() {
        return taskStatus.is(Condition.visible);
    }

    public boolean isFixVersionCorrect() {
        return fixVersion.is(Condition.visible);
    }
}
