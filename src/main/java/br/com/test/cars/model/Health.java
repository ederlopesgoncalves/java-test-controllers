package br.com.test.cars.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Health {
    private String status;
    private String date;
}
