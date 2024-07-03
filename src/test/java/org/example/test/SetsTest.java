package org.example.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

public class SetsTest extends BaseTest {

    @Test
    public void testGetAllSets() {
        Response response = given()
                .param("pageSize", 10)
                .when()
                .get("/sets")
                .then()
                .statusCode(200)
                .extract().response();

        assertEquals(response.jsonPath().getList("sets").size(), 10, "Expected 10 sets in the response");
    }

    @Test
    public void testGetAllSetsByName() {
        String _name = "Tenth Edition";
        Response response = given()
                .param("name", _name)
                .when()
                .get("/sets")
                .then()
                .statusCode(200)
                .extract().response();

        List<String> nameValues = response.jsonPath().getList("sets.name");

        for (String name : nameValues) {
            assertTrue(name.toLowerCase().contains(_name.toLowerCase()), "Expected name to contain " + _name + " for all sets");
        }
    }

    @Test
    public void testGetAllSetsByBlock() {
        String _block = "Core Set";
        Response response = given()
                .param("block", _block)
                .when()
                .get("/sets")
                .then()
                .statusCode(200)
                .extract().response();

        List<String> blockValues = response.jsonPath().getList("sets.block");

        for (String block : blockValues) {
            assertTrue(block.toLowerCase().contains(_block.toLowerCase()), "Expected name to contain " + _block + " for all sets");
        }
    }

    @Test
    public void testGetSetById() {
        String setId = "10E";
        given()
                .pathParam("id", setId)
                .when()
                .get("/sets/{id}")
                .then()
                .statusCode(200)
                .body("set.code", equalTo(setId));
    }

    @Test
    public void testGetSetByInvalidId() {
        String invalidSetId = "invalidId";
        given()
                .pathParam("id", invalidSetId)
                .when()
                .get("/sets/{id}")
                .then()
                .statusCode(404);
    }
}