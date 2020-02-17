package com.timely;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
       http.csrf().disable().authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers(HttpMethod.POST,"/manager").permitAll()
         .antMatchers(HttpMethod.GET,"/manager").permitAll()
         .antMatchers(HttpMethod.PUT,"/manager/{id}").permitAll()
         .antMatchers(HttpMethod.DELETE,"/manager/{id}").permitAll()
        .anyRequest().authenticated();
    }
}