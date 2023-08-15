package com.rhanem.bookseller.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfig  {

    private CustomDetailsUserService userDetailsService;





    public SecurityConfig(CustomDetailsUserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        //authenticationConfiguration.authenticationManagerBuilder().userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    @Bean
    public GlobalAuthenticationConfigurerAdapter authenticationConfigurerAdapter(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        return new GlobalAuthenticationConfigurerAdapter() {
            @Override
            public void init(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(userDetailsService)
                        .passwordEncoder(passwordEncoder);
            }
        };
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors(withDefaults()).csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers("/api/authentication/**").permitAll();
                    authConfig.anyRequest().authenticated();

                })
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(withDefaults()); //Customizer.withDefaults() Disable CSRF for demonstration purposes;

        return http.build(); // Build and return the SecurityFilterChain


    }




    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
         return (web -> web.ignoring().requestMatchers("/images/**","/js/**", "/webjars/**"));
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource () {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://127.0.0.1:8080")); // Add your allowed origins
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","PATCH")); // Add your allowed methods
        // configuration.addAllowedHeader("*"); // Add your allowed headers
        configuration.setAllowedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true); // Allow sending credentials like cookies
        configuration.setMaxAge(3600L); // Set the max age of preflight requests

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply this configuration to all paths

        return source;
    }


}
