package br.com.expert_security.domain.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.stream.Collectors;


public class CustomAuthentication implements Authentication {

    private final UsersIdentification identification;

    public CustomAuthentication(UsersIdentification identification) {
        if(identification == null){
            throw new ExceptionInInitializerError("It is not possible to create a custom authentication without the user identification");
        }
        this.identification = identification;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.identification.getPermissions()
                .stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.identification;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("It is already authenticated");
    }

    @Override
    public String getName() {
        return this.identification.getName();
    }
}
