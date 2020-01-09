package com.taokaixiang.ssm.demo.v1.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class StandardConfiguration {

    @Bean(name = "jacksonHttpMessageConverter")
    public MappingJackson2HttpMessageConverter JacksonHttpMessageConverter() {
        MappingJackson2HttpMessageConverter jacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jacksonHttpMessageConverter.setPrefixJson(false);
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        jacksonHttpMessageConverter.setObjectMapper(new ObjectMapper());
        jacksonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        return jacksonHttpMessageConverter;
    }

    @Bean
    public RestTemplate restTemplate(@Autowired MappingJackson2HttpMessageConverter jacksonHttpMessageConverter) throws JsonProcessingException {
        // 添加内容转换器,使用默认的内容转换器
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());

        restTemplate.getMessageConverters().add(jacksonHttpMessageConverter);

//        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
//        HttpMessageConverter<?> converter2 = new MappingJackson2HttpMessageConverter();
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        messageConverters.add(converter);
//        messageConverters.add(converter2);
//        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

}
