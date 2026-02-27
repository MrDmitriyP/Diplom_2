package praktikum.user;

import org.junit.After;

public abstract class UserTests {
    public UserSteps steps = new UserSteps();
    public String accessToken;
    public String accessTokenOne;
    public String accessTokenTwo;
    public String email = UserData.EMAIL;
    public String newEmail = UserData.NEW_EMAIL;
    public String password = UserData.PASSWORD;
    public String name = UserData.NAME;
    public String newName = UserData.NEW_NAME;

    @After
    public void tearDown() {
        if (accessToken != null)
            steps.deleteUser(accessToken).checkDeleteUser();
        if (accessTokenOne != null)
            steps.deleteUser(accessTokenOne).checkDeleteUser();
        if (accessTokenTwo != null)
            steps.deleteUser(accessTokenTwo).checkDeleteUser();
    }
}

