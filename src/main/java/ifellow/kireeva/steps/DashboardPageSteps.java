package ifellow.kireeva.steps;

import ifellow.kireeva.pages.DashboardPage;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;


public class DashboardPageSteps {


    @Тогда("перейти в проект Test")
    public void userNavigatesToTestProject() {
        DashboardPage dashboardPage = new DashboardPage();
        Assertions.assertTrue(
                dashboardPage.isOpenProjectTest(),
                "'Открытые задачи' не видно на странице"
        );
    }


}
