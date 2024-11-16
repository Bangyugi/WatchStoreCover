package com.group2.watchstorecover.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }


    /**
     * The {@link SecurityFilterChain} bean that is used to configure security for
     * the application. It disables CSRF protection and allows cross-origin requests
     * from any origin. It also allows all HTTP methods and headers.
     *
     * @param httpSecurity the {@link HttpSecurity} object to configure
     * @return a {@link SecurityFilterChain} that is used to filter incoming requests
     * @throws Exception if there is an error configuring security
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> corsFilter())
                .authorizeHttpRequests( authorize ->
                        {
                        authorize.requestMatchers(
                                "swagger-ui/index.html" +"/**"
                                , "/v3/api-docs/**"
                                , "/swagger-ui/**"
                        ).permitAll();

                        }
                );
        return httpSecurity.build();
    }

    /**
     * @return a {@link CorsFilter} that will be used to handle CORS requests. This filter will allow CORS requests from any origin,
     * and will allow all methods and headers.
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
