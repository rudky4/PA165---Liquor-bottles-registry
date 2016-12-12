package cz.muni.fi.pa165.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import sun.java2d.pipe.SpanShapeRenderer;

/**
 * Created by martin on 11/12/16.
 */
public class SimpleGrantedAuthorityFactory {

    private final static String ROLE_PREFIX = "ROLE_";

    public static SimpleGrantedAuthority createAuthority(String role) {
        return new SimpleGrantedAuthority(ROLE_PREFIX + role);
    }

}
