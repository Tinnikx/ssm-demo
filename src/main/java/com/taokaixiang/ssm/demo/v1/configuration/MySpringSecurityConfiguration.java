package com.taokaixiang.ssm.demo.v1.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
//@EnableWebSecurity
//@EnableWebFluxSecurity
public class MySpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 配置spring-security 拦截配置等的地方
//        http.antMatcher("/testApplicationContext").authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests().antMatchers("/testApplicationContext").permitAll();
    }
}
