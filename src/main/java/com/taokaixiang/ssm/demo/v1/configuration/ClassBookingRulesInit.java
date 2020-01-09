package com.taokaixiang.ssm.demo.v1.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Component
public class ClassBookingRulesInit implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassBookingRulesInit.class);

    @Resource
    private Map<String, HashMap<String, Integer>> classBookingRulesConfigurations;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initClassBookingConfigurations();
    }

    private void initClassBookingConfigurations() {
        InputStream rulesStream = ClassBookingRulesInit.class.getResourceAsStream("/class_booking_rules.json");
        try {
            Map<String, HashMap<String, Integer>> configurations = new ObjectMapper().readValue(rulesStream, new TypeReference<Map<String, HashMap<String, Integer>>>() {
            });
            classBookingRulesConfigurations.putAll(configurations);
            System.out.println();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
