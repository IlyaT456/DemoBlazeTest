package demoblaze.test;

import demoblaze.base.BaseTest;
import demoblaze.page.DemoBlazePage;
import org.testng.annotations.Test;

import java.util.List;

public class DemoBlazeTest extends BaseTest {

    private final String userLogin = "User123user123";
    private final String userPassword = "User123user123";

    @Test(description = "Регистрация нового пользователя")
    public void testRegistrationNewUser() {
        DemoBlazePage demoBlazePage = new DemoBlazePage(driver);
        String expectedAlertText = "Sign up successful.";

        demoBlazePage.openDemoBlazePage()
                .newUserRegistration(userLogin, userPassword);

        demoBlazePage.assertRegistrationNewUser(expectedAlertText);
    }

    @Test(description = "Авторизация пользователя")
    public void testLoginUser() {
        DemoBlazePage demoBlazePage = new DemoBlazePage(driver);
        String expected = "Welcome " + userLogin;

        demoBlazePage.openDemoBlazePage()
                .loginUser(userLogin, userPassword);

        demoBlazePage.assertLoginUser(expected);
    }

    @Test(description = "Категория групп тестового списка")
    public void testCategoryMenu() {
        DemoBlazePage demoBlazePage = new DemoBlazePage(driver);
        List<String> expectedCatMenu = List.of("Phones", "Laptops", "Monitors");

        demoBlazePage.openDemoBlazePage()
                .loginUser(userLogin, userPassword);

        demoBlazePage.assertCategoryMenu(expectedCatMenu);
    }

    @Test(description = "Добавления товара в корзину")
    public void addingItemToCart() {
        DemoBlazePage demoBlazePage = new DemoBlazePage(driver);
        String expectedAlertText = "Product added.";

        demoBlazePage.openDemoBlazePage()
                .loginUser(userLogin, userPassword)
                .selectItemCategory("Laptops")
                .selectCardTitle("MacBook Pro")
                .clickAddToCartButton();

        demoBlazePage.assertAddToCart(expectedAlertText);
    }
}
