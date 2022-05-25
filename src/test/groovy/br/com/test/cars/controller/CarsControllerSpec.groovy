package br.com.test.cars.controller

import br.com.test.cars.model.Car
import br.com.test.cars.service.CarService
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

/**
 * A Spock Spring MVC Rest unit test that doesn't require a full spring context
 */
class CarsControllerSpec extends Specification {

    def carController = new CarsController()

    def carService = Mock(CarService)

    MockMvc mockMvc = standaloneSetup(carController).build()

    def setup() {
        carController.carService = carService
    }

    def "shouldReturnSuccess_WhenFindCar"() {
        when: 'rest url is called'
        def response = mockMvc
                .perform(get("/cars/{codigo}", 1L))
                .andReturn().response

        then: 'service correctly returned car JSON'
        1 * carService.findCar(1L) >> new Car(
                1L, "Toyota","Yaris",
                2022, 2022,"Prata")

        expect: "Testing the HTTP Status code and json response string"
        response.status == OK.value()
        response.contentAsString == '{"code":1,"brand":"Toyota","model":"Yaris","yearManufacture":2022,"yearModel":2022,"color":"Prata"}'
    }

    def "shouldReturnNotFound_WhenFindCar"() {

        when: 'rest url is called'
        def response = mockMvc
                .perform(get("/cars/{codigo}", 5L))
                .andReturn().response

        then: 'service correctly returned null'
        1 * carService.findCar(5L) >> null

        expect: "Testing the HTTP Status code and json response string"
        response.status == NOT_FOUND.value()
        response.contentAsString == ''
    }

    def "shouldReturnBadRequest_WhenFindCar"() {

        when: 'rest url is called'
        def response = mockMvc
                .perform(get("/cars/{codigo}", -1L))
                .andReturn().response

        then: 'service not called'
        0 * carService.findCar(-1L)

        expect: "Testing the HTTP Status code and json response string"
        response.status == BAD_REQUEST.value()
        response.contentAsString == ''
    }
}