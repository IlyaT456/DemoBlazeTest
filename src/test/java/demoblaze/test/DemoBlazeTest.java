package demoblaze.test;

import demoblaze.base.BaseTest;
import demoblaze.page.DemoBlazePage;
import org.testng.annotations.Test;

import java.util.List;

import static demoblaze.read.ReadProperties.readProperties;

public class DemoBlazeTest extends BaseTest {

    private final String USER_LOGIN = readProperties("user.login");
    private final String USER_PASSWORD = readProperties("user.password");

    @Test(description = "Регистрация нового пользователя")
    public void testRegistrationNewUser() {
        DemoBlazePage demoBlazePage = new DemoBlazePage(driver);
        String expectedAlertText = "Sign up successful.";

        demoBlazePage.openDemoBlazePage()
                .newUserRegistration(USER_LOGIN, USER_PASSWORD);

        demoBlazePage.assertRegistrationNewUser(expectedAlertText);
    }

    @Test(description = "Авторизация пользователя")
    public void testLoginUser() {
        DemoBlazePage demoBlazePage = new DemoBlazePage(driver);
        String expected = "Welcome " + USER_LOGIN;

        demoBlazePage.openDemoBlazePage()
                .loginUser(USER_LOGIN, USER_PASSWORD);

        demoBlazePage.assertLoginUser(expected);
    }

    @Test(description = "Категория групп тестового списка")
    public void testCategoryMenu() {
        DemoBlazePage demoBlazePage = new DemoBlazePage(driver);
        List<String> expectedCatMenu = List.of("Phones", "Laptops", "Monitors");

        demoBlazePage.openDemoBlazePage()
                .loginUser(USER_LOGIN, USER_PASSWORD);

        demoBlazePage.assertCategoryMenu(expectedCatMenu);
    }

    @Test(description = "Добавления товара в корзину")
    public void addingItemToCart() {
        DemoBlazePage demoBlazePage = new DemoBlazePage(driver);
        String expectedAlertText = "Product added.";

        demoBlazePage.openDemoBlazePage()
                .loginUser(USER_LOGIN, USER_PASSWORD)
                .selectItemCategory("Laptops")
                .selectCardTitle("MacBook Pro")
                .clickAddToCartButton();

        demoBlazePage.assertAddToCart(expectedAlertText);
    }
}
