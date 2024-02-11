import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.Random;

import static org.junit.Assert.assertEquals;


public class LoginCourierTest {

    Random random = new Random();
    int randomNumber = random.nextInt(100);
    String login = "Garri" + randomNumber;
    String password = "1234";
    CreateCourier createCourier = new CreateCourier("", "", "");
    LoginCourier loginCourier = new LoginCourier("", "");

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        DeleteCourier deleteCourier = new DeleteCourier("", "");
        deleteCourier.deleteCourier(login, password);
        createCourier.createCourier(login, password, "Potter");
    }

    @Test // Авторизация курьера. Код 200
    @DisplayName("authorizationCourier") // имя теста
    @Description("Авторизация курьера. Код 200") // описание теста
    public void checkIdCourier() {
        // Step1 Проверка код 200
        int statusCode = loginCourier.checkStatusCodeCourier(login, password);
        assertEquals(200, statusCode);
        // Step2 Проверка id курьера
        Long IdCourier = loginCourier.checkIdCourier(login, password);
        Assert.assertTrue(IdCourier != 0);
    }

    @Test // Авторизация курьера. Без логина. Код 400
    @DisplayName("authorizationCourierNologin") // имя теста
    @Description("Авторизация курьера. Без логина. Код 400") // описание теста
    public void authorizationCourierNologin() {
        int statusCode = loginCourier.checkStatusCodeCourier("", password);
        assertEquals(400, statusCode);
    }

    @Test // Авторизация курьера. Без пароля. Код 400
    @DisplayName("authorizationCourierNoPassword") // имя теста
    @Description("Авторизация курьера. Без пароля. Код 400") // описание теста
    public void authorizationCourierNoPassword() {
        int statusCode = loginCourier.checkStatusCodeCourier(login, "");
        assertEquals(400, statusCode);
    }

    @Test // Авторизация курьера. Не существующий клиент. Код 404
    @DisplayName("authorizationCourierNoUser") // имя теста
    @Description("Авторизация курьера. Не существующий клиент. Код 404") // описание теста
    public void authorizationCourierNoUser() {
        int statusCode = loginCourier.checkStatusCodeCourier(login + "NoUser", password);
        assertEquals(404, statusCode);
    }

    @Test // Авторизация курьера. Не верный пароль. Код 404
    @DisplayName("authorizationCourierIncorrectPassword") // имя теста
    @Description("Авторизация курьера. Не верный пароль. Код 404") // описание теста
    public void authorizationCourierIncorrectPassword() {
        int statusCode = loginCourier.checkStatusCodeCourier(login, "12345");
        assertEquals(404, statusCode);
    }


    @After // Удаление курьера
    @DisplayName("deleteCourierTest") // имя теста
    @Description("Удаление курьера") // описание теста
    public void deleteCourierTest() {
        DeleteCourier deleteCourier = new DeleteCourier("", "");
        deleteCourier.deleteCourier(login, password);
    }

}