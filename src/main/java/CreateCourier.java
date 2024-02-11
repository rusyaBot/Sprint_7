import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateCourier {
    private String login;
    private String password;
    private String firstName;
    private int statusCode;


    public CreateCourier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CreateCourier(String login, String password) {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int createCourier(String login, String password, String firstName) {    // Создание курьера
        CreateCourier createCourier = new CreateCourier(login, password, firstName);
        Response response =
                given()
                        .header("Content-type", "application/json") // заполнил header
                        .log().all().body(createCourier) // заполни body
                        .when()
                        .post("/api/v1/courier");// отправить запрос в ручку
        response.then().assertThat();
        if (response.statusCode() == 200) {
            response.then().assertThat().body("ok", equalTo(true));
            return response.statusCode();
        }
        System.out.println("Запрос на создание курьера с логином: " + login + " и паролем: " + password);
        return response.statusCode();
    }


}
