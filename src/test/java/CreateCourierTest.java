import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CreateCourierTest {
    Random random = new Random();
    int randomNumber = random.nextInt(100);
    String login = "Garri" + randomNumber;
    String password = "1234";
    CreateCourier createCourier = new CreateCourier("", "", "");

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        DeleteCourier deleteCourier = new DeleteCourier("", "");
        deleteCourier.deleteCourier(login, password);
    }


    @Test // Создание курьера. Ответ 201
    @DisplayName("createNewCourier") // имя теста
    @Description("Создание курьера. Ответ 201") // описание теста
    public void createNewCourier() {
        int statusCode = createCourier.createCourier(login, password, "Potter");
        assertEquals(statusCode,201);
    }

    @Test // Создание курьера нет пароля. Ответ 400
    @DisplayName("createNewCourierNoPassword") // имя теста
    @Description("Создание курьера нет пароля. Ответ 400") // описание теста
    public void createNewCourierNoPassword() {
        int statusCode = createCourier.createCourier(login, "", "Potter");
        assertEquals(statusCode,400);
    }

    @Test // Создание курьера нет логина. Ответ 400
    @DisplayName("createNewCourierNologin") // имя теста
    @Description("Создание курьера нет логина. Ответ 400") // описание теста
    public void createNewCourierNologin() {
        int statusCode = createCourier.createCourier("", password, "Potter");
        assertEquals(statusCode,400);
    }


    @Test // Создание курьера логин уже используется. Ответ 409
    @DisplayName("createNewCourierLoginInUse") // имя теста
    @Description("Создание курьера логин уже используется. Ответ 409") // описание теста
    public void createNewCourierLoginInUse() {
        // Step1 Создание курьера
        createCourier.createCourier(login, password, "Potter");
        // Step2 Создание курьера2 с тем же логином
        int statusCode = createCourier.createCourier(login, password, "Potter");
        assertEquals(statusCode,409);
    }

    @After // Удаление курьера
    @DisplayName("deleteCourierTest") // имя теста
    @Description("Удаление курьера") // описание теста
    public void deleteCourierTest() {
        DeleteCourier deleteCourier = new DeleteCourier("", "");
        deleteCourier.deleteCourier(login, password);
    }


}