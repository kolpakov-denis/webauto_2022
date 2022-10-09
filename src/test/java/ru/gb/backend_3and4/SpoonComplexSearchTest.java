package ru.gb.backend_3and4;

import api.response.ApiSearchResults;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.javacrumbs.jsonunit.JsonAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static net.javacrumbs.jsonunit.core.Option.IGNORING_VALUES;
import static org.hamcrest.Matchers.*;


public class SpoonComplexSearchTest extends BaseTest {


    @Test
    void complexSearchParamsTest1(){
       String sushi = given().spec(requestSpecification)
                .queryParam("query", "Japanese Sushi")
                .when()
                .get(getSpoon_url()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification)
                .and()
                .extract().response().jsonPath().getString("results[0].title");
        Assertions.assertEquals("Japanese Sushi", sushi);

    }
    @Test
    void complexSearchParamsTest2(){
        String sushi = given().spec(requestSpecification)
                .queryParam("cuisine", "Asian")
                .queryParam("includeIngredients", "salmon caviar")
                .queryParam("diet", "pescatarian,gluten free")
                .queryParam("maxFat", "13")
                .queryParam("maxReadyTime", "45")
                .when()
                .get(getSpoon_url()+"recipes/complexSearch")
                .then()
                .spec(responseSpecification)
                .and()
                .extract().response().jsonPath().getString("results[0].title");
        Assertions.assertEquals("Japanese Sushi", sushi);

    }
    @Test
    void ingredientsSearchTest1() {
        Response ingredientSearch = given().spec(requestSpecification)
                .queryParam("ingredients", getIngredient1() + "," + getIngredient2())
                .queryParam("number", "1")
                .queryParam("ranking", "1")
                .queryParam("ignorePantry", "true")
                .when()
                .get(getSpoon_url() + "recipes/findByIngredients")
                .then()
                .spec(responseSpecification)
                .and()
                .extract().response();

        String savedId = (ingredientSearch.getBody().jsonPath().getString("id[0]"));
        String savedName = (ingredientSearch.getBody().jsonPath().getString("title[0]"));



        Response result = given().spec(requestSpecification)
                .when()
                .get(getSpoon_url() + "recipes/" + savedId + "/summary")
                .then()
                .spec(responseSpecification)
                .and()
                .extract().response();
        Assertions.assertEquals(savedId+savedName,
                result.getBody().jsonPath().getString("id")+result.getBody().jsonPath().getString("title"));

    }

    @Test
    void testSearchBread() {

        ApiSearchResults actually = given()
                .param("number", 3)
                .param("limitLicense", true)
                .param("query", "bread")
                .log()
                .uri()
                .expect()
                .spec(responseSpecification)
                .body("totalResults", is(175))
                .body("results", hasSize(3))
                .log()
                .body()
                .when()
                .get("/recipes/complexSearch")
                .body()
                .as(ApiSearchResults.class);
        actually.getResults().get(0).getImage();

        String expected = readResourceAsString("expected.json");
        JsonAssert.assertJsonEquals(
                expected,
                actually,
                JsonAssert.when(IGNORING_ARRAY_ORDER)
        );
    }

    @Test
    void connectUserSuccess(){

        RestAssured.given()
                .body(Map.of("username", "random", "firstName", "random", "lastname", "random", "email", "random"))
                .expect()
                .spec(responseSpecification)
                .body("status", is("success"))
                .body("username", isA(String.class))
                .body("spoonacularPassword", isA(String.class))
                .body("hash", isA(String.class))
                .log()
                .all()
                .when()
                .post("/users/connect");
    }

    @Test
    void connectUserWithoutBodyFailure400(){

        given()
                .expect()
                .statusCode(400)
                .time(lessThanOrEqualTo(1500L))
                .header("Content-Type", "application/json")
                .body("status", is("failure"))
                .body("code", is(400))
                .body("message", is("Could not parse JSON body."))
                .log()
                .all()
                .when()
                .post("/users/connect");

    }

    @Test
    void generateShoppingListSuccessful200(){

        Object actual = given()
                .queryParam("hash", "cfb46db8e2cff3e37fe328a89320e14cf18efa8c")
                .pathParams("username", "murphy-erdman19")
                .pathParam("start-date", "2022-02-06")
                .pathParam("end-date", "2022-02-28")
                .expect()
                .spec(responseSpecification)
                .body("aisles", hasSize(0)) // actual is (LinkedHashMap), aisles is(ArrayList)
                .body("cost", is(0.0F))
                .body("startDate", is(1644105600))
                .body("endDate", is(1646006400))
                .when()
                .post("/mealplanner/{username}/shopping-list/{start-date}/{end-date}")
                .as(Object.class);
        System.out.println(actual);
    }

    @Test
    void addToShoppingListSuccessful200(){

        Object actualRes = given().spec(requestSpecification)
                .queryParam("hash", "cfb46db8e2cff3e37fe328a89320e14cf18efa8c")
                .pathParams("username", "murphy-erdman19")
                .body(Map.of("item", "1 package baking powder", "aisle", "Baking", "parse", true))
                .expect()
                .spec(responseSpecification)
                .when()
                .post("/mealplanner/{username}/shopping-list/items")
                .as(Object.class);

        String expected = readResourceAsString("addedToShoppingList.json");
        JsonAssert.assertJsonEquals(
                expected,
                actualRes,
                JsonAssert.when(IGNORING_VALUES));
    }

    @Test
    void getShoppingListSuccessful200(){

        LinkedHashMap actualRes = given().spec(requestSpecification)
                .queryParam("hash", "cfb46db8e2cff3e37fe328a89320e14cf18efa8c")
                .pathParams("username", "murphy-erdman19")
                .expect()
                .spec(responseSpecification)
                .body("aisles[0].items[0].name", is("baking powder"))
                .header("Content-Type", "application/json;charset=utf-8")
                .when()
                .get("mealplanner/{username}/shopping-list")
                .as(LinkedHashMap.class);
        Object cost = actualRes.get("cost");
        System.out.println(cost);


    }

    @Test
    void deleteShoppingListSuccessful200(){
        given()
                .queryParam("hash", "cfb46db8e2cff3e37fe328a89320e14cf18efa8c")
                .pathParams("username", "murphy-erdman19")
                .pathParam("id", "1297577")
                .expect()
                .spec(responseSpecification)
                .body("status", is("success"))
                .when()
                .delete("/mealplanner/{username}/shopping-list/items/{id}");
    }




}
