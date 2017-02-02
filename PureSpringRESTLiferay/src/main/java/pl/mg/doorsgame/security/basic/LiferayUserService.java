package pl.mg.doorsgame.security.basic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class LiferayUserService implements UserDetailsService {

    private static List<LiferayUser> users = new ArrayList<>();


    static {
        users.add(new LiferayUser("eco1", "eco1pass", "ECO"));
        users.add(new LiferayUser("admin", "adminpass", "ADMIN"));
        users.add(new LiferayUser("pm1", "pm1pass", "PM"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user = null;
        for (LiferayUser liferayUser : users) {
            if (liferayUser.getScreenname().equals(username)) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(liferayUser.getRole()));
                user = new User(liferayUser.getScreenname(), liferayUser.getPassword(), authorities);
            }
        }
        return user;
    }

}
