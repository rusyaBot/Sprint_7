import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Orders {

    private int orderTrack;
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public Orders(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }


    public int creatingOrder(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        Orders orders = new Orders(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        Response response = given()
                .header("Content-type", "application/json") // заполнил header
                .log().all().body(orders) // заполнил body
                .when()
                .post("/api/v1/orders")// отправить запрос в ручку
                .then().assertThat().extract().response(); // проверить статус
        return (int) response.statusCode();
    }

    public int creatingOrderGetTrack(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        Orders orders = new Orders(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        Response response = given()
                .header("Content-type", "application/json") // заполнил header
                .log().all().body(orders) // заполнил body
                .when()
                .post("/api/v1/orders")// отправить запрос в ручку
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
                        .get("/api/v1/orders/track?t=" + orderTrack);// отправить запрос в ручку
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
