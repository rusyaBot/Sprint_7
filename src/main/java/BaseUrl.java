import io.restassured.RestAssured;

public class BaseUrl {
      private String baseUrl = "https://qa-scooter.praktikum-services.ru/";

      public void getBaseUrl() {
            RestAssured.baseURI = baseUrl;
      }

}
