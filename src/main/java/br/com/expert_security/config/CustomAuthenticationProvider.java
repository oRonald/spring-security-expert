package br.com.expert_security.config;

import br.com.expert_security.domain.entity.Users;
import br.com.expert_security.domain.security.CustomAuthentication;
import br.com.expert_security.domain.security.UsersIdentification;
import br.com.expert_security.domain.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UsersService usersService;
    private final PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = (String) authentication.getCredentials();

        Users userWithPermissions = usersService.getUserWithPermissions(login);
        if(userWithPermissions != null){
            boolean matches = encoder.matches(password, userWithPermissions.getPassword());
            if(matches){
                UsersIdentification idUser = new UsersIdentification(userWithPermissions.getId(),
                        userWithPermissions.getName(), userWithPermissions.getLogin(),
                        userWithPermissions.getPermissions());

                return new CustomAuthentication(idUser);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
