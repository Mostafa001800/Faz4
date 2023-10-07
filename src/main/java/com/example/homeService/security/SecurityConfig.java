package com.example.homeService.security;

import com.example.homeService.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final PersonRepository personRepository;
    @SneakyThrows
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/register/**").permitAll()
                            .requestMatchers("/manager/**").hasAuthority("MANAGER")
                            .requestMatchers("/expert/**").hasAuthority("EXPERT")
                            .requestMatchers("/customer/**").hasAuthority("CUSTOMER")
                            .anyRequest().authenticated()
                    ).httpBasic(httpSecurityHttpBasicConfigurer ->{})
                    .authenticationProvider(new Provider((CustomDetailsService) userDetailsService(),passwordEncoder()));

            return http.build();
        }
    @Bean
    public AuthenticationProvider customAuthenticationProvider() {
        return new Provider((CustomDetailsService) userDetailsService(),passwordEncoder());
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomDetailsService(personRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
