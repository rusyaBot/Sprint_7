import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateCourierApi {
    private final String apiPath = "/api/v1/courier";
    public int createCourier(String login, String password, String firstName) {    // Создание курьера
        Courier courier = new Courier(login, password, firstName);
        Response response =
                given()
                        .header("Content-type", "application/json") // заполнил header
                        .body(courier) // заполни body
                        .when()
                        .post(apiPath);// отправить запрос в ручку
        response.then().assertThat();
        if (response.statusCode() == 200) {
            response.then().assertThat().body("ok", equalTo(true));
            return response.statusCode();
        }
        System.out.println("Запрос на создание курьера с логином: " + login + " и паролем: " + password);
        return response.statusCode();
    }


}
