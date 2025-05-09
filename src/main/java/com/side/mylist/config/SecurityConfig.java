package com.side.mylist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeHttpRequests(
                        (auth) -> auth
                                .requestMatchers("/" , "/login" , "/loginProc" , "/joinProc").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .requestMatchers("/my/**").hasAnyRole("ADMIN" , "USER")
                                .anyRequest().authenticated()
                );


        httpSecurity
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("loginProc")
                        .permitAll()
                );


        httpSecurity
                .csrf((auth) -> auth.disable()
                );


        return httpSecurity.build();

    }

}
