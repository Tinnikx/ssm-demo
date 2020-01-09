package com.taokaixiang.ssm.demo.v1.controller;

import com.taokaixiang.ssm.demo.v1.model.Car;
import com.taokaixiang.ssm.demo.v1.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TestController {

    @Value("${test.value}")
    private String testValue;

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private PersonService personService;

    @Resource
    private Map<String, HashMap<String, Integer>> classBookingRulesConfigurations;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/testBookingRules")
    public String testBookingRules() {
        Map<String, Integer> brix = classBookingRulesConfigurations.get("BRIX");
        return brix.entrySet().stream().map(map -> map.getKey() + " ---> " + map.getValue()).collect(Collectors.joining("\n"));
    }

    @GetMapping(value = "/test")
    public String test(HttpServletRequest request) {
        SecurityContextHolderAwareRequestWrapper request1 = (SecurityContextHolderAwareRequestWrapper) request;
        return SecurityContextHolder.getContext().getAuthentication().toString();
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
        HttpEntity<Car> objectHttpEntity = new HttpEntity<>(car, httpHeaders);
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
