package org.filip.controllers;

import io.quarkus.test.junit.QuarkusTest;
import org.filip.resources.Person;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PersonControllerTest {

    @Test
    public void testGetAPersonResource() {
        given()
                .when()
                .get("/persons/1")
                .then()
                .statusCode(200)
                .body(is("{\"id\":1,\"name\":\"Filip\",\"age\":30}"));
    }

    @Test
    public void testGetAllPersonResources() {
        given()
                .when()
                .get("/persons")
                .then()
                .statusCode(200)
                .body(is("[{\"id\":1,\"name\":\"Filip\",\"age\":30}]"));
    }

    @Test
    public void testAddAPersonResources() {
        given()
                .contentType("application/json")
                .body("{\"id\":2,\"name\":\"Ion\",\"age\":27}")
                .when()
                .post("/persons")
                .then()
                .statusCode(200)
                .body(is("{\"id\":2,\"name\":\"Ion\",\"age\":27}"));
    }

    @Test
    public void testUpdateAPersonResources() {
        given()
                .contentType("application/json")
                .body("{\"name\":\"Ion\",\"age\":27}")
                .when()
                .put("/persons/1")
                .then()
                .statusCode(200)
                .body(is("{\"id\":1,\"name\":\"Ion\",\"age\":27}"));
    }

    @Test
    public void testDeleteAPersonResources() {
        given()
                .when()
                .delete("/persons/1")
                .then()
                .statusCode(200)
                .body(is("{\"id\":1,\"name\":\"Filip\",\"age\":30}"));
    }

    @Test
    public void testSingPersonResource() {
        given()
                .when()
                .get("/persons/1/sing")
                .then()
                .statusCode(200)
                .body(is("Person{id=1, name='Filip', age=30} sings: Lalalala"));
    }
}
