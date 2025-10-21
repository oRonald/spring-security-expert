package br.com.expert_security.config;

import br.com.expert_security.domain.security.CustomAuthentication;
import br.com.expert_security.domain.security.UsersIdentification;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class CustomFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String secretHeader = request.getHeader("x-secret");
        if(secretHeader != null){
            if (secretHeader.equals("secr3t")) {
                var usersIdentification = new UsersIdentification(
                        "id-secret",
                        "secret",
                        "x-secret",
                        List.of("USER")
                );
                Authentication authentication = new CustomAuthentication(usersIdentification);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
