import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;


public class OrderApiTest {

    BaseUrl baseUrl = new BaseUrl();
    private final String apiPath = "/api/v1/orders";
    @Before
    public void setUp() {
        baseUrl.getBaseUrl();}

    @Test
    @DisplayName("checkOrder") // имя теста
    @Description("Проверил что вернулось в orders") // описание теста
    public void checkOrder() {
        ArrayList<String> orders = given()
                .get(apiPath)
                .then().assertThat().statusCode(200).extract().path("orders");
        assertTrue(orders.size() > 0);
    }
}