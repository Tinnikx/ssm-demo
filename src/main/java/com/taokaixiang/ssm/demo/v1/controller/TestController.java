package com.taokaixiang.ssm.demo.v1.controller;

import com.taokaixiang.ssm.demo.v1.model.Car;
import com.taokaixiang.ssm.demo.v1.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
//@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/test")
    public String test() {
        return personService.createOrUpdate().toString();
    }

    @GetMapping(value = "/testJson")
    public Car testJson() {
        Car car = new Car();
        car.setColor("red");
        car.setName("tersla");
        LOGGER.info(car.toString());
        return car;
    }

    @GetMapping(value = "/testApplicationContext")
    @PreAuthorize("hasPermission(null, null, null )")
    public WebApplicationContext testApplicationContext() {
        WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
        return applicationContext;
    }

    @PostMapping(value = "/complexParameter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car getComplexParameter(Car car) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.set("name", "123");
//        body.set("color", "123");
        HttpEntity<Car> objectHttpEntity = new HttpEntity<>(car, httpHeaders);
//        httpHeaders.set
        ResponseEntity<Car> exchange = restTemplate.exchange("http://localhost:8081/test", HttpMethod.POST, objectHttpEntity, Car.class);
        return exchange.getBody();
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("12300");
        list.add("456");
        System.out.println(list);
    }

    @ExceptionHandler(value = ClassNotFoundException.class)
    public String resolveException(HttpServletRequest request) {
        return "123";
    }
}
