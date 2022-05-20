package br.com.test.cars.service;

import org.springframework.stereotype.Service;

import br.com.test.cars.model.Car;

@Service
public class CarService {

    private static final int MAX_CODE = 100;

    public Car findCar(final Long code) {

        if (code > MAX_CODE) {
            return null;
        }

        return new Car(
            code,
            "Toyota",
			"Corolla",
			2007,
			2008,
			"Prata"
		);
	}
}
