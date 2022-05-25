package br.com.test.cars.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.test.cars.model.Car;
import br.com.test.cars.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import io.restassured.http.ContentType;

@WebMvcTest
public class CarControllerTest {

	@Autowired
	private CarsController carController;

	@MockBean
	private CarService carService;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.carController);
	}

	@Test
	public void shouldReturnSuccess_WhenFindCar() {

		when(this.carService.findCar(1L))
			.thenReturn(new Car(
                    1L, "Toyota","Yaris",
                    2022, 2022,"Prata"));

		given()
			.accept(ContentType.JSON)
		.when()
			.get("/cars/{codigo}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void shouldReturnNotFound_WhenFindCar() {

		when(this.carService.findCar(5L))
			.thenReturn(null);

		given()
			.accept(ContentType.JSON)
		.when()
			.get("/cars/{codigo}", 5L)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}

	@Test
	public void shouldReturnBadRequest_WhenFindCar() {

		given()
			.accept(ContentType.JSON)
		.when()
			.get("/cars/{codigo}", -1L)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());

		verify(this.carService, never()).findCar(-1L);
	}
}
