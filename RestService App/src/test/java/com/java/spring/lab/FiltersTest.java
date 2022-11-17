package com.java.spring.lab;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FiltersTest {

    @LocalServerPort
    int localPort;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:" + localPort;
    }

    @Test
    public void shouldListItems() {
        given().get("/items")
                .then().statusCode(200)
                .and().body("field1", contains("Sam", "Jose", "Samuel"))
                .and().body("field2", contains("Jaus", "Carvajal", "Ubu"));
    }

    @Test
    public void shouldFilterByField1Equals() {
        given().queryParam("filter", "field1::Sam")
                .get("/items")
                .then().statusCode(200)
                .and().body("field1", contains("Sam"))
                .and().body("field2", contains("Jaus"));
    }

    @Test
    public void shouldFilterByBooleanEquals() {
        given().queryParam("filter", "active::true")
                .get("/items")
                .then().statusCode(200)
                .and().body("field1", contains("Jose"))
                .and().body("field2", contains("Carvajal"));
    }

    @Test
    public void shouldFilterByIntegerEquals() {
        given().queryParam("filter", "number::20")
                .get("/items")
                .then().statusCode(200)
                .and().body("field1", contains("Jose"))
                .and().body("field2", contains("Carvajal"));
    }

    @Test
    public void shouldFilterByMultipleEquals() {
        given().queryParam("filter", "field1::Sam")
                .queryParam("filter", "field1::NotExist")
                .get("/items")
                .then().statusCode(200)
                .body("", Matchers.hasSize(0));
    }

    @Test
    public void shouldFilterByField1Contains() {
        given().queryParam("filter", "field1::Sam")
                .get("/items")
                .then().statusCode(200)
                .and().body("field1", contains("Sam"))
                .and().body("field2", contains("Jaus"));
    }
}
