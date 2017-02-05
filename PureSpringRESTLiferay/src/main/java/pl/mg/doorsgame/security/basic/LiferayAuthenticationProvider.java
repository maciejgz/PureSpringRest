package pl.mg.doorsgame.security.basic;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import pl.mg.doorsgame.liferay.service.UserService;

//@Component("liferayAuthenticationProvider")
public class LiferayAuthenticationProvider implements AuthenticationProvider {

    public LiferayAuthenticationProvider() {
        super();
    }

    //@Autowired
    UserService userService = new UserService();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (userService == null) {
            System.out.println("user service is null");
        }
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println("authentication provider: " + name + ";" + password);

        if (userService.authenticateByScreename(name, password)) {

            final UserDetails principal = new User(name, password, new ArrayList<GrantedAuthority>());
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, new ArrayList<GrantedAuthority>());
            return auth;
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
