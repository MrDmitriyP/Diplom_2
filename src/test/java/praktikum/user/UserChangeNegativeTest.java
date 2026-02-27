package praktikum.user;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;

@Feature("Изменение данных пользователя: Негативные тесты")
public class UserChangeNegativeTest extends UserTests {

    @Before
    public void setUp() {
        UserRegisterRequest userOne = new UserRegisterRequest(email, password, name);
        accessTokenOne = steps.registerUser(userOne).checkRegisterUser();
    }

    @Test
    @DisplayName("Нельзя изменить данные пользователя без авторизации")
    @Description("Проверка, что возвращает 401 код при попытке изменения профиля без авторизации")
    public void testUserChangeUnauthorized() {
        UserChangeRequest user = new UserChangeRequest(newEmail, newName);
        steps.changeUser(user).checkChangeUserUnauthorized();
    }

    @Test
    @DisplayName("Нельзя изменить email на уже занятый другим пользователем")
    @Description("Проверка, что возвращает 403 код при попытке установить email, который уже используется")
    public void testUserChangeWithEmailAlreadyExists() {
        UserRegisterRequest userTwo = new UserRegisterRequest(newEmail, password, name);
        accessTokenTwo = steps.registerUser(userTwo).checkRegisterUserTwo();

        UserChangeRequest user = new UserChangeRequest(email, newName);
        steps.changeUser(user, accessTokenTwo).checkChangeUserEmailExists();
    }

}