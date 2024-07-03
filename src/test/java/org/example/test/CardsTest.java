package org.example.test;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertTrue;

public class CardsTest extends BaseTest {

    @Test
    public void testGetAllCards() {
        Response response = given()
                .param("pageSize", 10)
                .when()
                .get("/cards")
                .then()
                .statusCode(200)
                .extract().response();

        assertEquals(response.jsonPath().getList("cards").size(), 10, "Expected 10 cards in the response");
    }

    @Test
    public void testGetAllCardsWithCMCFive() {
        Response response = given()
                .param("cmc", 5)
                .when()
                .get("/cards")
                .then()
                .statusCode(200)
                .extract().response();

        List<Float> cmcValues = response.jsonPath().getList("cards.cmc");

        for (Float cmc : cmcValues) {
            assertEquals(cmc.floatValue(), 5, "Expected cmc to be 5 for all cards");
        }
    }

    @Test
    public void testGetCardById() {
        String cardId = "386616"; // Example card ID
        given()
                .pathParam("id", cardId)
                .when()
                .get("/cards/{id}")
                .then()
                .statusCode(200)
                .body("card.multiverseid", equalTo(cardId));
    }

    @Test
    public void testGetCardByInvalidId() {
        String invalidCardId = "invalidId";
        given()
                .pathParam("id", invalidCardId)
                .when()
                .get("/cards/{id}")
                .then()
                .statusCode(404);
    }

    @Test
    public void testGetCardByInvalidCMC() {
        given()
                .param("cmc", "cmc")
                .when()
                .get("/cards")
                .then()
                .statusCode(400);
    }
}