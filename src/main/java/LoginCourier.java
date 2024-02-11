import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginCourier {
    private String login;
    private String password;

    public LoginCourier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long checkIdCourier(String login, String password) {     // Получение id курьера, нужен для создания курьера
        LoginCourier loginCourier = new LoginCourier(login, password);
        Response response =
                given()
                        .header("Content-type", "application/json") // заполнил header
                        .body(loginCourier) // заполнил body
                        .when()
                        .post("/api/v1/courier/login")// отправить запрос на ручку
                        .then().extract().response();
        if (response.statusCode() == 200) {
            return response.jsonPath().getLong("id");
        } else {
            return 0L;
        }
    }

    public int checkStatusCodeCourier(String login, String password) {     // Получение id курьера, нужен для создания курьера
        LoginCourier loginCourier = new LoginCourier(login, password);
        Response response =
                given()
                        .header("Content-type", "application/json") // заполнил header
                        .log().all().body(loginCourier) // заполнил body
                        .when()
                        .post("/api/v1/courier/login")// отправить запрос на ручку
                        .then().extract().response();
        return (int) response.statusCode();
    }

}


