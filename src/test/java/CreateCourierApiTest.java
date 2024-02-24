import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CreateCourierApiTest {
    Random random = new Random();
    int randomNumber = random.nextInt(100);
    String login = "Garri" + randomNumber;
    String password = "1234";
    CreateCourierApi createCourierApi = new CreateCourierApi();
    BaseUrl baseUrl = new BaseUrl();
    @Before
    public void setUp() {
        baseUrl.getBaseUrl();
        DeleteCourierApi deleteCourierApi = new DeleteCourierApi();
        deleteCourierApi.deleteCourier(login, password);
    }

    @Test // Создание курьера. Ответ 201
    @DisplayName("createNewCourier") // имя теста
    @Description("Создание курьера. Ответ 201") // описание теста
    public void createNewCourier() {
        int statusCode = createCourierApi.createCourier(login, password, "Potter");
        assertEquals(201, statusCode);
    }

    @Test // Создание курьера нет пароля. Ответ 400
    @DisplayName("createNewCourierNoPassword") // имя теста
    @Description("Создание курьера нет пароля. Ответ 400") // описание теста
    public void createNewCourierNoPassword() {
        int statusCode = createCourierApi.createCourier(login, "", "Potter");
        assertEquals(400, statusCode);
    }

    @Test // Создание курьера нет логина. Ответ 400
    @DisplayName("createNewCourierNologin") // имя теста
    @Description("Создание курьера нет логина. Ответ 400") // описание теста
    public void createNewCourierNologin() {
        int statusCode = createCourierApi.createCourier("", password, "Potter");
        assertEquals(400, statusCode);
    }


    @Test // Создание курьера логин уже используется. Ответ 409
    @DisplayName("createNewCourierLoginInUse") // имя теста
    @Description("Создание курьера логин уже используется. Ответ 409") // описание теста
    public void createNewCourierLoginInUse() {
        // Step1 Создание курьера
        createCourierApi.createCourier(login, password, "Potter");
        // Step2 Создание курьера2 с тем же логином
        int statusCode = createCourierApi.createCourier(login, password, "Potter");
        assertEquals(409, statusCode);
    }

    @After // Удаление курьера
    public void deleteCourier() {
        DeleteCourierApi deleteCourierApi = new DeleteCourierApi();
        deleteCourierApi.deleteCourier(login, password);
    }


}