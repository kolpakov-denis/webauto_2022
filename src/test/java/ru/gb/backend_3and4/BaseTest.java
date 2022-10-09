package ru.gb.backend_3and4;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.Properties;

public class BaseTest {
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String spoon_key;
    private static String spoon_url;
    private static String ingredient1;
    private static String ingredient2;
    protected static ResponseSpecification responseSpecification;
    protected static RequestSpecification requestSpecification;



    @BeforeAll
    static void initTest() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        spoon_key =  prop.getProperty("spoon_key");
        spoon_url= prop.getProperty("spoon_url");
        ingredient1 = prop.getProperty("ingredient1");
        ingredient2 = prop.getProperty("ingredient2");

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", spoon_key)
                .addQueryParam("includeNutrition", "false")
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.responseSpecification = responseSpecification;
        RestAssured.requestSpecification = requestSpecification;


    }



    public static String getSpoon_key() {
        return spoon_key;
    }

    public static String getSpoon_url() {
        return spoon_url;
    }
    public static String getIngredient1() {
        return ingredient1;
    }
    public static String getIngredient2() {
        return ingredient2;
    }

    public RequestSpecification getRequestSpecification(){
        return requestSpecification;
    }

   public String readResourceAsString(String resourceName) {
        String path = getClass().getSimpleName() + FileSystems.getDefault().getSeparator() + resourceName;
        try (InputStream inputStream = getClass().getResourceAsStream(path)) {
            assert inputStream != null;
            byte[] data = inputStream.readAllBytes();
            return new String(data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
