package br.com.test.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.test.cars.model.Car;
import br.com.test.cars.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarsController {

	@Autowired
	private CarService carService;

	@GetMapping("/{codigo}")
	public ResponseEntity<Car> findCar(final @PathVariable Long codigo) {

		if (codigo < 0) {
			return ResponseEntity.badRequest().build();
		}

		Car car = this.carService.findCar(codigo);

		if (car == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(car);
	}
}
