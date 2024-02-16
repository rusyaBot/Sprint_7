import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;


public class OrderApiTest {

    OrderApi orderApi = new OrderApi();
    BaseUrl baseUrl = new BaseUrl();
    @Before
    public void setUp() {
        baseUrl.getBaseUrl();}


    @Test // Я не понимаю что хотят в задании от меня общие фразы путают, -Проверь, что в тело ответа возвращается список заказов.
            // есть отдельный нормальный тест. достал свой заказ по его номеру и проверил построчно.
    @DisplayName("checkNotNull") // имя теста
    @Description("Что то да возвращает, проверил что вернулось не пусто") // описание теста
    public void ordersListNotNull() {
        Response response = orderApi.ordersListNotNull();
        assertNotNull(response);
        System.out.println(response.prettyPrint());
    }

}