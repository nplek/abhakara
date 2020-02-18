package com.abhakara.admiralapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.abhakara.admiralapi.entity.Foo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SpringBootTest
class AdmiralApplicationTests {

	@Test
	void contextLoads() {
	}

	private RequestSpecification givenAuth(String username, String password) {
		FormAuthConfig formAuthConfig = 
		  new FormAuthConfig("http://localhost:8080/login", "username", "password");
		 
		return RestAssured.given().auth().form(username, password, formAuthConfig);
	}

	@Test
	public void givenUserWithReadPrivilegeAndHasPermission_whenGetFooById_thenOK() {
		Response response = givenAuth("admin@email.com", "pass").get("http://localhost:8080/foos/1");
		assertEquals(200, response.getStatusCode());
		assertTrue(response.asString().contains("id"));
	}

	@Test
	public void givenUserWithNoWritePrivilegeAndHasPermission_whenPostFoo_thenForbidden() {
		Response response = givenAuth("john", "123").contentType(MediaType.APPLICATION_JSON_VALUE)
													.body(new Foo("sample"))
													.post("http://localhost:8080/foos");
		assertEquals(403, response.getStatusCode());
	}
	
	@Test
	public void givenUserWithWritePrivilegeAndHasPermission_whenPostFoo_thenOk() {
		Response response = givenAuth("tom", "111").contentType(MediaType.APPLICATION_JSON_VALUE)
												.body(new Foo("sample"))
												.post("http://localhost:8080/foos");
		assertEquals(201, response.getStatusCode());
		assertTrue(response.asString().contains("id"));
	}

}
