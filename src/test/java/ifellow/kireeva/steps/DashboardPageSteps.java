package ifellow.kireeva.steps;

import ifellow.kireeva.pages.DashboardPage;
import io.cucumber.java.ru.И;
import org.junit.jupiter.api.Assertions;


public class DashboardPageSteps {


    @И("перейти в проект Test")
    public void userNavigatesToTestProject() {
        DashboardPage dashboardPage = new DashboardPage();
        Assertions.assertTrue(
                dashboardPage.isOpenProjectTest(),
                "'Открытые задачи' не видно на странице"
        );
    }


}
