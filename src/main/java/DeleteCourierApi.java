import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteCourierApi {

    public void deleteCourier(String login, String password) {
        // Удаление курьера через доп проверки, для стабильности тестов.
        // Вариант логин есть, но пароль не верный, не делал, так как не найти id курьера если не подходит логин+парроль.
        Courier courier = new Courier(login, password);
        Response response1 =
                given()
                        .header("Content-type", "application/json") // заполнил header
                        .body(courier) // заполнил body
                        .when()
                        .post("/api/v1/courier/login")// отправить запрос в ручку
                        .then().extract().response();

        if (response1.statusCode() == 200) {  // Проверка, есть ли клиент с логином + паролем для удаления, то удаляем
            Long id = response1.jsonPath().getLong("id");
            Response response2 =
                    given()
                            .header("Content-type", "application/json") // заполнил header
                            .when()
                            .delete("/api/v1/courier/" + id);// отправить запрос в ручку
            System.out.println("Курьер c id: " + id + " и логином: " + login + " удалён");
        } else if (response1.statusCode() == 404) { //Если нет, то выводим нет курьера для удаления
            System.out.println("Нет курьера с логином: " + login + "  и паролем: " + password + " для удаления");
        }
    }
}
