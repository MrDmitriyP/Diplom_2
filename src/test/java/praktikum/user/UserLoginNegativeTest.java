package praktikum.user;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;


@Feature("Авторизация пользователя: Негативные тесты")
public class UserLoginNegativeTest extends UserTests{

    @Before
    public void setUp() {
        UserRegisterRequest user =new UserRegisterRequest(UserData.EMAIL, UserData.PASSWORD, UserData.NAME);
        accessToken = steps.registerUser(user).checkRegisterUser();
    }


    @Test
    @DisplayName("Нельзя авторизоваться с пустым паролем")
    @Description("Проверка, что API отдает 401 код при передаче null в поле password")
    public void testUserLoginWithEmptyPassword() {
        UserLoginRequest user = new UserLoginRequest(UserData.EMAIL, null);
        steps.loginUser(user, accessToken).checkNegativeLoginUser();
    }

    @Test
    @DisplayName("Нельзя авторизоваться с email, которого нет")
    @Description("Проверка, что API отдает 401 код при попытке входа с невалидным email")
    public void testUserLoginWithEmailInNotSystem() {
        UserLoginRequest user = new UserLoginRequest(UserData.NEW_EMAIL, UserData.PASSWORD);
        steps.loginUser(user, accessToken).checkNegativeLoginUser();
    }

    @Test
    @DisplayName("Нельзя авторизоваться с пустым email")
    @Description("Проверка, что API отдает 401 код при передаче null в поле email")
    public void testUserLoginWithEmptyEmail() {
        UserLoginRequest user = new UserLoginRequest(null, UserData.PASSWORD);
        steps.loginUser(user, accessToken).checkNegativeLoginUser();
    }

    @Test
    @DisplayName("Нельзя авторизоваться с невалидным паролем")
    @Description("Проверка, что API отдает 401 код при вводе неверного пароля")
    public void testUserLoginWithInvalidPassword() {
        UserLoginRequest user = new UserLoginRequest(UserData.EMAIL, "invalid");
        steps.loginUser(user, accessToken).checkNegativeLoginUser();
    }

}