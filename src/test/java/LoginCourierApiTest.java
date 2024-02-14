import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.Random;

import static org.junit.Assert.assertEquals;


public class LoginCourierApiTest extends Constant{

    Random random = new Random();
    int randomNumber = random.nextInt(100);
    String login = "Garri" + randomNumber;
    String password = "1234";
    CreateCourierApi createCourierApi = new CreateCourierApi();
    LoginCourierApi loginCourierApi = new LoginCourierApi();

    @Before
    public void setUp() {
        RestAssured.baseURI = BASEURI;
        DeleteCourierApi deleteCourierApi = new DeleteCourierApi();
        deleteCourierApi.deleteCourier(login, password);
        createCourierApi.createCourier(login, password, "Potter");
    }

    @Test // Авторизация курьера. Код 200
    @DisplayName("authorizationCourier") // имя теста
    @Description("Авторизация курьера. Код 200") // описание теста
    public void checkIdCourier() {
        // Step1 Проверка код 200
        int statusCode = loginCourierApi.checkStatusCodeCourier(login, password);
        assertEquals(200, statusCode);
        // Step2 Проверка id курьера
        Long idCourier = loginCourierApi.checkIdCourier(login, password);
        Assert.assertTrue(idCourier != 0);
    }

    @Test // Авторизация курьера. Без логина. Код 400
    @DisplayName("authorizationCourierNologin") // имя теста
    @Description("Авторизация курьера. Без логина. Код 400") // описание теста
    public void authorizationCourierNologin() {
        int statusCode = loginCourierApi.checkStatusCodeCourier("", password);
        assertEquals(400, statusCode);
    }

    @Test // Авторизация курьера. Без пароля. Код 400
    @DisplayName("authorizationCourierNoPassword") // имя теста
    @Description("Авторизация курьера. Без пароля. Код 400") // описание теста
    public void authorizationCourierNoPassword() {
        int statusCode = loginCourierApi.checkStatusCodeCourier(login, "");
        assertEquals(400, statusCode);
    }

    @Test // Авторизация курьера. Не существующий клиент. Код 404
    @DisplayName("authorizationCourierNoUser") // имя теста
    @Description("Авторизация курьера. Не существующий клиент. Код 404") // описание теста
    public void authorizationCourierNoUser() {
        int statusCode = loginCourierApi.checkStatusCodeCourier(login + "NoUser", password);
        assertEquals(404, statusCode);
    }

    @Test // Авторизация курьера. Не верный пароль. Код 404
    @DisplayName("authorizationCourierIncorrectPassword") // имя теста
    @Description("Авторизация курьера. Не верный пароль. Код 404") // описание теста
    public void authorizationCourierIncorrectPassword() {
        int statusCode = loginCourierApi.checkStatusCodeCourier(login, "12345");
        assertEquals(404, statusCode);
    }


    @After // Удаление курьера
    @DisplayName("deleteCourier") // имя
    @Description("Удаление курьера") // описание
    public void deleteCourierTest() {
        DeleteCourierApi deleteCourierApi = new DeleteCourierApi();
        deleteCourierApi.deleteCourier(login, password);
    }

}