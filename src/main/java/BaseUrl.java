import io.restassured.RestAssured;

public class BaseUrl {
      private String BaseUrl = "https://qa-scooter.praktikum-services.ru/";

      public void getBaseUrl() {
            RestAssured.baseURI = BaseUrl;
      }

}
