package praktikum.order;

import org.junit.After;
import praktikum.user.UserData;
import praktikum.user.UserSteps;

import java.util.List;

public abstract class OrderTests {
    public String accessToken;
    public List<String> ingredients;
    public OrderSteps orderSteps = new OrderSteps();
    public UserSteps userSteps = new UserSteps();
    public String email = UserData.EMAIL;
    public String password = UserData.PASSWORD;
    public String name = UserData.NAME;
    public String bunId;
    public String mainId;
    public String sauceId;

    @After
    public void tearDown() {
        userSteps.deleteUser(accessToken).checkDeleteUser();
    }
}
