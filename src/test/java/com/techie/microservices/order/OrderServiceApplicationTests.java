package com.techie.microservices.order;


import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import com.techie.microservices.order.stubs.InventoryClientStub;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port=0)
class OrderServiceApplicationTests {

	@ServiceConnection
	static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");
	@LocalServerPort
	private Integer port;
	
	@BeforeEach
	void setup() {
		RestAssured.baseURI="http://localhost";
		RestAssured.port=port;
	}
	
	static {
		mySQLContainer.start();
	}
	@Test
	void shouldSubmtOrder() {
		String submitOrderJson="""
				{
				    "skuCode": "iphone_15",
				    "quantity": 1,
				    "price": 132443
				}
				""";
		
		InventoryClientStub.stubInventoryCall("iphone_15", 1);
		var resBodyString=RestAssured.given()
		.contentType("application/json")
		.body(submitOrderJson)
		.when()
		.post("/api/order/insert")
		.then()
		.log().all()
		.statusCode(201)
		.extract()
		.body().asString();
		
		assertThat(resBodyString, Matchers.is("Order Placed Successfully"));
	}

}
