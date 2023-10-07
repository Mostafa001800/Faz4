package com.example.homeService.security;

import com.example.homeService.base.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Provider implements AuthenticationProvider {
    private final CustomDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Person person = (Person) customUserDetailsService.loadUserByUsername(authentication.getName());
        if (passwordEncoder.matches(((String) authentication.getCredentials()), person.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    person, null, person.getAuthorities()
            );
        }
        throw new BadCredentialsException("wrong information");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
