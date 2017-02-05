package pl.mg.doorsgame.security.basic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import pl.mg.doorsgame.liferay.service.UserService;
import pl.mg.doorsgame.model.LiferayUser;

@Component
public class LiferayUserService implements UserDetailsService {

    @Autowired
    UserService userService;

    /*static {
        private static List<LiferayUser> users = new ArrayList<>();
    
        users.add(new LiferayUser("eco1", "eco1", "ECO"));
        users.add(new LiferayUser("admin", "admin", "ADMIN"));
        users.add(new LiferayUser("pm1", "pm1", "PM"));
    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LiferayUser user = userService.findUserByScreenname(username);
        User authUser = null;
        if (user != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (String roleName : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(roleName));
            }
            authUser = new User(user.getUsername(), user.getPassword(), authorities);
        }
        return authUser;
    }

}
