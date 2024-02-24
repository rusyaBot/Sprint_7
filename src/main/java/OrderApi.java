import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class OrderApi {
    private final String apiPath = "/api/v1/orders";

    public Response creatOrder(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        Response response = given()
                .header("Content-type", "application/json") // заполнил header
                .body(order) // заполнил body
                .when()
                .post(apiPath)// отправить запрос в ручку
                .then().assertThat().extract().response(); // проверить статус
        return response;
    }

    public int creatOrderGetTrack(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        Response response = given()
                .header("Content-type", "application/json") // заполнил header
                .body(order) // заполнил body
                .when()
                .post(apiPath)// отправить запрос в ручку
                .then().assertThat().extract().response(); // проверить статус
        if (response.statusCode() == 201) {
            return response.jsonPath().getInt("track");
        } else {
            return 0;
        }
    }

    public void ordersList(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color, int orderTrack) {
        Response response =
                given()
                        .get(apiPath + "/track?t=" + orderTrack);// отправить запрос в ручку
        response.then().assertThat()
                .and().body("order.firstName", equalTo(firstName))
                .and().body("order.lastName", equalTo(lastName))
                .and().body("order.address", equalTo(address))
                .and().body("order.metroStation", equalTo(metroStation))
                .and().body("order.phone", equalTo(phone))
                .and().body("order.rentTime", equalTo(rentTime))
                .and().body("order.deliveryDate", equalTo(deliveryDate))
                .and().body("order.comment", equalTo(comment))
                .and().body("order.color", equalTo(color))
                .and().body("order.track", equalTo(orderTrack));
    }

}
