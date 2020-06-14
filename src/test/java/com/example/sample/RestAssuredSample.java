package com.example.sample;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.RestAssured;

public class RestAssuredSample {

	private final static Logger log = LoggerFactory.getLogger("TestLog");

	@Test
	public void LivedoorGetSample() {
		try {
			RestAssured.baseURI = "http://weather.livedoor.com/forecast/webservice/json/v1";
	        given()
	        	.param("city", "471010")
	            .get("")
	        .then()
	        	.statusCode(200)
	        	.body("location.city", equalTo("那覇"));
	        log.info("success");
		} catch (AssertionError e) {
			log.error(e.getLocalizedMessage());
		}
	}

	@Test
	public void HttpbinGetSample() {
		RestAssured.baseURI = "http://httpbin.org/get";
        given()
        	.param("a", "1")
        	.param("b", "2")
            .get("")
        .then()
        	.statusCode(200)
        	.body("args.a", equalTo("1"))
        	.body("args.b", equalTo("2"));
	}

	@Test
	public void HttpbinPostSample01() {
		RestAssured.baseURI = "http://httpbin.org/post";
        given()
        	.param("param1", "aa")
        	.param("param2", "bb")
            .post("")
        .then()
        	.statusCode(200)
        	.body("form.param1", equalTo("aa"))
        	.body("form.param2", equalTo("bb"));
	}

	@Test
	public void HttpbinPostSample02() {
		RestAssured.baseURI = "http://httpbin.org/post";
		String json = "{\"param1\":\"aa\",\"param2\":\"bb\"}";
        given()
        	.header("Content-Type", "application/json")
        	.body(json)
            .post("")
        .then()
        	.statusCode(200)
        	.body("json.param1", equalTo("aa"))
        	.body("json.param2", equalTo("bb"));
	}

	@Test
	public void JsonPlaceholderGetSample() throws IOException {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts/1";
        given()
        	.get("")
        .then()
        	.statusCode(200)
        	.body("userId", equalTo(1));
	}

	@Test
	public void JsonPlaceholderPostSample() throws IOException {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts/";
		String json = "{\"title\":\"a1 test\",\"body\":\"this is test by a1.\",\"userId\":1}";
        given()
        	.header("Content-Type", "application/json")
        	.body(json)
        	.post("")
        .then()
        	.statusCode(201)
        	.body("title", equalTo("a1 test"));
	}

}
