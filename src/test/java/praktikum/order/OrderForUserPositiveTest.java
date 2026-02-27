package praktikum.order;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import praktikum.user.UserRegisterRequest;

import java.util.Arrays;

@Feature("Получение заказов пользователя: Позитивные тесты")
public class OrderForUserPositiveTest extends OrderTests {


    @Before
    public void setUp() {
        UserRegisterRequest user = new UserRegisterRequest(email, password, name);
        accessToken = userSteps.registerUser(user).checkRegisterUser();

        OrderSteps ingredientsResponse = orderSteps.getAllIngredients();
        bunId = ingredientsResponse.getFirstBunId();
        mainId = ingredientsResponse.getFirstMainId();
        sauceId = ingredientsResponse.getFirstSauceId();
        ingredients = Arrays.asList(bunId, mainId, sauceId);

        OrderCreateRequest order = new OrderCreateRequest(ingredients);
        orderSteps.createOrder(order, accessToken);
    }

    @Test
    @DisplayName("Авторизованный пользователь может получить свои заказы")
    @Description("Проверка, что авторизованный пользователь может успешно получить список своих заказов через API")
    public void getOrderForUser() {
        orderSteps.getOrderForUser(accessToken).checkGetOrderForUser();
    }

}