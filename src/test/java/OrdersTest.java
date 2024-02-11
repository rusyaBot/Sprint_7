
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

@RunWith(Parameterized.class)
public class OrdersTest {
    private List<String> color;
    private int statusCode;

    public OrdersTest(List<String> color, int statusCode) {
        this.color = color;
        this.statusCode = statusCode;
    }

    @Parameterized.Parameters
    public static Object[][] getColor() {
        //Тестовые данные
        return new Object[][]{
                {List.of("BLACK", "GREY"), 201},
                {List.of("GREY"), 201},
                {List.of("BLACK"), 201},
                {List.of(), 201},
        };
    }

    Orders orders = new Orders("", "", "", "", "", 0, "", "", color);

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test // Создание заказа. Ответ 201
    @DisplayName("createNewOrders") // имя теста
    @Description("Создание заказа. Ответ 201") // описание теста
    public void createNewOrders() {
        int ordersTrack = orders.creatingOrder(
                "Garri",
                "Potter",
                "Hogwarts 777",
                "4",
                "+7 800 355 35 35",
                5,
                "2020-06-06",
                "Valera, nastalo tvoyo vremya",
                color);
        System.out.println(ordersTrack);
        Assert.assertTrue(ordersTrack != 0);
    }

    @Test // Создание-Ответ 201. Проверка заказа в списке -Ответ 200.
    @DisplayName("checkNewOrders") // имя теста
    @Description("Создание-Ответ 201. Проверка заказа в списке -Ответ 200.") // описание теста
    public void checkNewOrders() {
        int ordersTrack = orders.creatingOrderGetTrack(
                "Garri",
                "Potter",
                "Hogwarts 777",
                "4",
                "+7 800 355 35 35",
                5,
                "2020-06-06",
                "Valera, nastalo tvoyo vremya",
                color);
        orders.ordersList(
                "Garri",
                "Potter",
                "Hogwarts 777",
                "4",
                "+7 800 355 35 35",
                5,
                "2020-06-06T00:00:00.000Z",
                "Valera, nastalo tvoyo vremya",
                color,
                ordersTrack);
    }

}