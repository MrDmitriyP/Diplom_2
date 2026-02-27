package praktikum.order;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import praktikum.user.UserRegisterRequest;

import java.util.Arrays;


@Feature("Создание заказа: Негативные тесты")
public class OrderCreateNegativeTest extends OrderTests {

    @Before
    public void setUp() {
        UserRegisterRequest user = new UserRegisterRequest(email, password, name);
        accessToken = userSteps.registerUser(user).checkRegisterUser();

        OrderSteps ingredientsResponse = orderSteps.getAllIngredients();
        bunId = ingredientsResponse.getFirstBunId();
        mainId = ingredientsResponse.getFirstMainId();
        sauceId = "invalidSauceId";
        ingredients = Arrays.asList(bunId, mainId, sauceId);
    }

    @Test
    @DisplayName("Невозможно создать заказ без ингредиентов")
    @Description("Проверка, что API возвращает ошибку при попытке создать заказ без указания ингредиентов")
    public void testCreateOrderWithoutIngredient() {
        OrderCreateRequest order = new OrderCreateRequest();
        orderSteps.createOrder(order).checkNegativeCreateOrderWithoutIngredient();
    }

    @Test
    @DisplayName("Невозможно создать заказ с невалидным id ингредиента")
    @Description("Проверка, что API возвращает ошибку при попытке создать заказ, содержащий ингредиент с несуществующим ID")
    public void testCreateOrderWithInvalidIngredientId() {
        OrderCreateRequest order = new OrderCreateRequest(ingredients);
        orderSteps.createOrder(order).checkNegativeCreateOrderWithInvalidIngredientId();
    }


}