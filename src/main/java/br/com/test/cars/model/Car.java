package br.com.test.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private Long code;
    private String brand;
    private String model;
    private int yearManufacture;
    private int yearModel;
    private String color;

}
