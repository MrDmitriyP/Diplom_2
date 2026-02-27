package praktikum.user;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;

@Feature("Регистрация пользователя: Позитивные тесты")
public class UserRegisterPositiveTest extends UserTests{

    @Test
    @DisplayName("Регистрация с валидными данными")
    @Description("Проверка успешной регистрации с валидным email, паролем и именем. Ожидается 200 код и токен")
    public void testUserRegister() {
        UserRegisterRequest user = new UserRegisterRequest(UserData.EMAIL, UserData.PASSWORD, UserData.NAME);
        accessToken = steps.registerUser(user).checkRegisterUser();
    }

}