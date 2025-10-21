package br.com.expert_security.config;

import br.com.expert_security.domain.security.CustomAuthentication;
import br.com.expert_security.domain.security.UsersIdentification;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MasterPasswordAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var login = authentication.getName();
        var password = (String) authentication.getCredentials();

        String masterLogin = "master";
        String masterePass = "@321";

        if(masterLogin.equals(login) && masterePass.equals(password)){
            UsersIdentification usersIdentification = new UsersIdentification("master",
                    "master", masterLogin, List.of("ADMIN"));
            return new CustomAuthentication(usersIdentification);
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
