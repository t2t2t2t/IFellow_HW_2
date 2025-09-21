package ifellow.kireeva;

import ifellow.kireeva.pages.LoginPage;
import ifellow.kireeva.util.CustomProperties;

public class LoginHelper extends WebHook {

    public static void performLogin() {
        LoginPage loginPage = new LoginPage();
        String username = CustomProperties.getInstance().getProperty("username");
        String password = CustomProperties.getInstance().getProperty("password");
        loginPage.login(username, password);
    }
}
