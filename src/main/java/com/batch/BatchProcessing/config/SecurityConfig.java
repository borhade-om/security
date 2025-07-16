package com.batch.BatchProcessing.config;

import com.batch.BatchProcessing.repository.UserRepository;
import com.batch.BatchProcessing.service.UserService;
import jakarta.servlet.http.HttpServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final UserDetailsService userDetailsService;
    private final JWTFilter jwtFilter;

//    @Lazy
//    public SecurityConfig(UserDetailsService userDetailsService, JWTFilter jwtFilter) {
//        this.userDetailsService = userDetailsService;
//        this.jwtFilter = jwtFilter;
//    }


    @Lazy
    @Autowired
    public SecurityConfig(UserService userService, JWTFilter jwtFilter) {
        this.userDetailsService = userService;
        this.jwtFilter = jwtFilter;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      httpSecurity
              .csrf(csrf ->csrf.disable() )
              .authorizeHttpRequests(request-> request
                      .requestMatchers("/v3/api-docs", "/v3/api-docs/**","/swagger-ui/**", "/swagger-ui.html","/swagger-ui/index.html#")
                      .permitAll()
                      .requestMatchers("/User/**","/User/signin")
                      .permitAll()
                      .anyRequest()
                      .authenticated())
              .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
       return httpSecurity.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(14);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
      provider.setUserDetailsService(userDetailsService); // use injected service
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
