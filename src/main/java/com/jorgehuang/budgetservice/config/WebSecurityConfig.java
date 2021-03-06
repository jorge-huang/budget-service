package com.jorgehuang.budgetservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    private void configureForDevProfile(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated();

        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.headers().frameOptions().sameOrigin();
        http.csrf().disable(); // TODO: find out how to implement CSRF
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (environment.acceptsProfiles(Profiles.of("dev"))) {
            configureForDevProfile(http);
            return;
        }

        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}