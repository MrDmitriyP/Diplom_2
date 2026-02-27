package praktikum.order;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import praktikum.user.UserRegisterRequest;

import java.util.Arrays;

@Feature("Создание заказа: Позитивные тесты")
public class OrderCreatePositiveTest extends OrderTests {

    @Before
    public void setUp() {
        UserRegisterRequest user = new UserRegisterRequest(email, password, name);
        accessToken = userSteps.registerUser(user).checkRegisterUser();

        OrderSteps ingredientsResponse = orderSteps.getAllIngredients();
        bunId = ingredientsResponse.getFirstBunId();
        mainId = ingredientsResponse.getFirstMainId();
        sauceId = ingredientsResponse.getFirstSauceId();
        ingredients = Arrays.asList(bunId, mainId, sauceId);
    }

    @Test
    @DisplayName("Авторизованный пользователь может создать заказ")
    @Description("Проверка, что авторизованный пользователь может успешно создать заказ с валидными ингредиентами")
    public void testCreateOrder() {
        OrderCreateRequest order = new OrderCreateRequest(ingredients);
        orderSteps.createOrder(order, accessToken).checkCreateOrder();
    }

    @Test
    @DisplayName("Неавторизованный пользователь может создать заказ")
    @Description("Проверка, что пользователь без авторизации может успешно создать заказ с валидными ингредиентами")
    public void testCreateOrderUnauthorized() {
        OrderCreateRequest order = new OrderCreateRequest(ingredients);
        orderSteps.createOrder(order).checkCreateOrder();
    }

}