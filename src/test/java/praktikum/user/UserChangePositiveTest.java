package praktikum.user;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;

@Feature("Изменение данных пользователя: Позитивные тесты")
public class UserChangePositiveTest extends UserTests {

    @Before
    public void setUp() {
        UserRegisterRequest user = new UserRegisterRequest(email, password, name);
        accessToken = steps.registerUser(user).checkRegisterUser();
    }

    @Test
    @DisplayName("Пользователь может изменить email и имя вместе")
    @Description("Проверка успешного обновления email и имени пользователем. Ожидается 200 код и возврат данных")
    public void testUserChange() {
        UserChangeRequest user = new UserChangeRequest(newEmail, newName);
        steps.changeUser(user, accessToken).checkChangeUser(newEmail, newName);
    }

    @Test
    @DisplayName("Пользователь может изменить только имя")
    @Description("Проверка, что пользователь может обновить только имя, без email. Ожидается код 200 и возврат данных")
    public void testUserChangeOnlyName() {
        UserChangeRequest user = new UserChangeRequest(null, newName);
        steps.changeUser(user, accessToken).checkChangeUser(email, newName);
    }

    @Test
    @DisplayName("Пользователь может изменить email")
    @Description("Проверка, что пользователь может обновить только email, без имени. Ожидается 200 код и возврат данных")
    public void testUserChangeOnlyEmail() {
        UserChangeRequest user = new UserChangeRequest(newEmail, null);
        steps.changeUser(user, accessToken).checkChangeUser(newEmail, name);
    }

}