package org.example.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertNotNull;

public class TypesTest extends BaseTest {

    @Test
    public void testGetTypes() {
        Response response = given()
                .when()
                .get("/types")
                .then()
                .statusCode(200)
                .body("types", notNullValue())
                .extract().response();

        assertNotNull(response.jsonPath().getList("types"), "Expected list of types to be not null");
    }

    @Test
    public void testGetSubtypes() {
        Response response = given()
                .when()
                .get("/subtypes")
                .then()
                .statusCode(200)
                .body("subtypes", notNullValue())
                .extract().response();

        assertNotNull(response.jsonPath().getList("subtypes"), "Expected list of subtypes to be not null");
    }

    @Test
    public void testGetSupertypes() {
        Response response = given()
                .when()
                .get("/supertypes")
                .then()
                .statusCode(200)
                .body("supertypes", notNullValue())
                .extract().response();

        assertNotNull(response.jsonPath().getList("supertypes"), "Expected list of supertypes to be not null");
    }

    @Test
    public void testGetFormats() {
        Response response = given()
                .when()
                .get("/formats")
                .then()
                .statusCode(200)
                .body("formats", notNullValue())
                .extract().response();

        assertNotNull(response.jsonPath().getList("formats"), "Expected list of formats to be not null");
    }
}
