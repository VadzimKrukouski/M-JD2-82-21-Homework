package com.example.demo.config;

import com.example.demo.config.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                .antMatchers("/api/users").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/api/product/**","/api/recipe/**","/api/profile/**").hasAnyRole("ADMIN","USER")
//                .antMatchers(HttpMethod.POST,"/api/product/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.PUT,"/api/product/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers(HttpMethod.DELETE,"/api/product/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/api/recipe/**").hasAnyRole("ADMIN","USER")
//                .antMatchers(HttpMethod.PUT,"/api/recipe/**","/api/user/**").hasAnyRole("ADMIN","USER")
//                .antMatchers(HttpMethod.DELETE,"/api/recipe/**").hasAnyRole("ADMIN")
                .antMatchers("/api/register", "/api/auth").anonymous()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
