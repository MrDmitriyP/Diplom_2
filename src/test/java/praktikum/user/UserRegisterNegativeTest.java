package praktikum.user;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;

@Feature("Регистрация пользователя: Негативные тесты")
public class UserRegisterNegativeTest extends UserTests{

    @Test
    @DisplayName("Регистрация пользователя с существующим email")
    @Description("Проверка, API отдает 403 код при попытке регистрации пользователя с зарегистрированным email")
    public void testUserRegisterWithEmailAlreadyExists() {
        UserRegisterRequest userOne = new UserRegisterRequest(UserData.EMAIL, UserData.PASSWORD, UserData.NAME);
        accessToken = steps.registerUser(userOne).checkRegisterUser();

        UserRegisterRequest userTwo = new UserRegisterRequest(UserData.EMAIL, UserData.PASSWORD, UserData.NAME);
        steps.registerUser(userTwo).checkRegisterUserAlreadyExists();
    }

    @Test
    @DisplayName("Регистрация пользователя без указания email")
    @Description("Проверка, API отдает 400 код, если указать поле email: null")
    public void testUserRegisterWithoutEmail() {
        UserRegisterRequest user = new UserRegisterRequest(null, UserData.PASSWORD, UserData.NAME);
        steps.registerUser(user).checkRegisterUserMissingFields();
    }

    @Test
    @DisplayName("Регистрация пользователя с пустым email")
    @Description("Проверка, API отдает 400 код, если указать поле email: ''")
    public void testUserRegisterWithEmptyEmail() {
        UserRegisterRequest user = new UserRegisterRequest("", UserData.PASSWORD, UserData.NAME);
        steps.registerUser(user).checkRegisterUserMissingFields();
    }

    @Test
    @DisplayName("Регистрация без пароля")
    @Description("Проверка, API отдает 400 код, если указать поле password: null")
    public void testUserRegisterWithoutPassword() {
        UserRegisterRequest user = new UserRegisterRequest(UserData.EMAIL, null, UserData.NAME);
        steps.registerUser(user).checkRegisterUserMissingFields();
    }


    @Test
    @DisplayName("Регистрация пользователя с пустым паролем")
    @Description("Проверка, API отдает 400 код, если указать поле password: ''")
    public void testUserRegisterWithEmptyPassword() {
        UserRegisterRequest user = new UserRegisterRequest(UserData.EMAIL, "", UserData.NAME);
        steps.registerUser(user).checkRegisterUserMissingFields();
    }

    @Test
    @DisplayName("Регистрация пользователя без имени")
    @Description("Проверка, API отдает 400 код, если указать поле name: null")
    public void testUserRegisterWithoutName() {
        UserRegisterRequest user = new UserRegisterRequest(UserData.EMAIL, UserData.PASSWORD, null);
        steps.registerUser(user).checkRegisterUserMissingFields();
    }

    @Test
    @DisplayName("Регистрация пользователя с пустым именем")
    @Description("Проверка, API отдает 400 код, если в запросе указать поле name:''")
    public void testUserRegisterWithEmtyName() {
        UserRegisterRequest user = new UserRegisterRequest(UserData.EMAIL, UserData.PASSWORD, "");
        steps.registerUser(user).checkRegisterUserMissingFields();
    }

    @Test
    @DisplayName("Регистрация пользователя с пустыми параметрами")
    @Description("Проверка, API возвращает 400 код, если в запросе указать все поля = ''")
    public void testUserRegisterWithEmtyBody() {
        UserRegisterRequest user = new UserRegisterRequest("", "", "");
        steps.registerUser(user).checkRegisterUserMissingFields();
    }

    @Test
    @DisplayName("Нельзя зарегистрировать пользователя без тела запроса")
    @Description("Проверка, API отдает 400 код, если в запросе указать все поля = null")
    public void testUserRegisterWithoutParametersInBody() {
        UserRegisterRequest user = new UserRegisterRequest(null, null, null);
        steps.registerUser(user).checkRegisterUserMissingFields();
    }

}