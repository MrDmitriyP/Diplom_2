package praktikum.user;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;

@Feature("Авторизация пользователя: Позитивные тесты")
public class UserLoginPositiveTest extends UserTests{

    @Before
    public void setUp() {
        UserRegisterRequest user =new UserRegisterRequest(UserData.EMAIL, UserData.PASSWORD, UserData.NAME);
        accessToken = steps.registerUser(user).checkRegisterUser();
    }

    @Test
    @DisplayName("Пользователь может успешно авторизоваться с валидными данными")
    @Description("Проверка, авторизации пользователя в систему с валидным email и паролем. Ожидаем 200 код,получение данных пользователя с токеном")
    public void testUserLogin() {
        UserLoginRequest user = new UserLoginRequest(UserData.EMAIL, UserData.PASSWORD);
        steps.loginUser(user, accessToken).checkLoginUser();
    }

}