package cz.muni.fi.pa165.security;

import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.facade.PersonFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author mhajas
 */

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Inject
    private PersonFacade personFacade;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        if (personFacade.authenticate(username, password)) {

            PersonDTO person = personFacade.findUserByLogin(username);

            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + person.getRole().toString()));


            return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
        }else {
            throw new BadCredentialsException("Wrong password.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
