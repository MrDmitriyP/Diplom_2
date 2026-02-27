package praktikum.order;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

@Feature("Получение заказов пользователя: Негативные тесты")
public class OrderForUserNegativeTest extends OrderTests {

    @Before
    public void setUp() {
        OrderSteps ingredientsResponse = orderSteps.getAllIngredients();
        bunId = ingredientsResponse.getFirstBunId();
        mainId = ingredientsResponse.getFirstMainId();
        sauceId = ingredientsResponse.getFirstSauceId();
        ingredients = Arrays.asList(bunId, mainId, sauceId);

        OrderCreateRequest order = new OrderCreateRequest(ingredients);
        orderSteps.createOrder(order).checkCreateOrder();
    }

    @Test
    @DisplayName("Невозможно получить заказы пользователя без авторизации")
    @Description("Проверка, что API возвращает ошибку 401 при попытке получить список заказов без авторизации")
    public void testGetOrderForUserUnauthorized() {
        orderSteps.getOrderForUser().checkNegativeGetOrderForUserWithoutToken();
    }
}