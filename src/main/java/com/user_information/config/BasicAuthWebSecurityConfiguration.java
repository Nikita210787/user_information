package com.user_information.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
    /**
     * Basic autentifications
    * # <a href="https://howtodoinjava.com/spring-boot2/security-rest-basic-auth-example/">https://howtodoinjava.com/spring-boot2/security-rest-basic-auth-example/</a>
    * # or we can add in aplication.yaml:
    * # security:
    * #   user:
    * #     name: admin
    * #     password: admin
    * #    roles: ADMIN
    */
@Configuration
public class BasicAuthWebSecurityConfiguration
{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User
                .withUsername("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
