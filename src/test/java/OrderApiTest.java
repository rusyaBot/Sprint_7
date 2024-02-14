import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;


public class OrderApiTest extends Constant{

    OrderApi orderApi = new OrderApi();
    @Before
    public void setUp() {
        RestAssured.baseURI = BASEURI;
    }


    @Test // Проверка, что возвращается список заказов
    @DisplayName("checkNotNull") // имя теста
    @Description("Проверка, что возвращается список заказов") // описание теста
    public void ordersListNotNull() {
        Response response = orderApi.ordersListNotNull();
        assertNotNull(response);
        System.out.println(response.prettyPrint());
    }

}