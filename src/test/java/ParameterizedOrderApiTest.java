import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class ParameterizedOrderApiTest {
    private List<String> color;
    private int statusCode;

    public ParameterizedOrderApiTest(List<String> color, int statusCode) {
        this.color = color;
        this.statusCode = statusCode;
    }

    OrderApi orderApi = new OrderApi();

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

    BaseUrl baseUrl = new BaseUrl();

    @Before
    public void setUp() {
        baseUrl.getBaseUrl();
    }

    @Test // Создание заказа. Ответ 201
    @DisplayName("createNewOrders") // имя теста
    @Description("Создание заказа. Ответ 201 и тело ответа track не пусто") // описание теста
    public void createNewOrders() {
        Response response = orderApi.creatOrder(
                "Garri",
                "Potter",
                "Hogwarts 777",
                "4",
                "+7 800 355 35 35",
                5,
                "2020-06-06",
                "Valera, nastalo tvoyo vremya",
                color);
        response.then().assertThat()
                .statusCode(statusCode)
                .and().body("track", notNullValue());
    }

    @Test // Создание-Ответ 201. Проверка заказа в списке -Ответ 200.
    @DisplayName("checkNewOrders") // имя теста
    @Description("Создание-Ответ 201. Проверка заказа в списке -Ответ 200.") // описание теста
    public void checkNewOrders() {
        int ordersTrack = orderApi.creatOrderGetTrack(
                "Garri",
                "Potter",
                "Hogwarts 777",
                "4",
                "+7 800 355 35 35",
                5,
                "2020-06-06",
                "Valera, nastalo tvoyo vremya",
                color);
        orderApi.ordersList(
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