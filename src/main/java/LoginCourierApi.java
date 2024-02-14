import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginCourierApi {
    private final String apiPath = "/api/v1/courier/login";
    public Long checkIdCourier(String login, String password) {     // Получение id курьера, нужен для создания курьера
        Courier courier = new Courier(login, password);
        Response response =
                given()
                        .header("Content-type", "application/json") // заполнил header
                        .body(courier) // заполнил body
                        .when()
                        .post(apiPath)// отправить запрос на ручку
                        .then().extract().response();
        if (response.statusCode() == 200) {
            return response.jsonPath().getLong("id");
        } else {
            return 0L;
        }
    }

    public int checkStatusCodeCourier(String login, String password) {     // Получение id курьера, нужен для создания курьера
        Courier courier = new Courier(login, password);
        Response response =
                given()
                        .header("Content-type", "application/json") // заполнил header
                        .body(courier) // заполнил body
                        .when()
                        .post(apiPath)// отправить запрос на ручку
                        .then().extract().response();
        return (int) response.statusCode();
    }

}


