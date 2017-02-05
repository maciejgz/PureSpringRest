package pl.mg.doorsgame.security.basic;

import java.util.Arrays;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Klasa konfiguracyjna dla spring security
 * @author m
 *
 */
//@Configuration
//@EnableWebSecurity
//@ComponentScan("pl.mg")
public class BasicSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static String REALM = "MY_TEST_REALM";

    //@Autowired
    LiferayAuthenticationProvider liferayAuthenticationProvider;

    /**
     * Override to configure userdetails services. tu wstawiamy user details service
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(liferayAuthenticationProvider);
    }

    /**
     * Override to configure Spring Security's filter chain
     */
    /* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/hello/**");
    }


    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Arrays.asList((AuthenticationProvider) new LiferayAuthenticationProvider()));
    }

    /**
     * Konfiguracja jak requesty są chronione przez interceptory - tu ustalamy jakie adresy jakiej ochrony wymagają
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/hello/**").authenticated().and().httpBasic();
    }



    //old type
    /*    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("bill").password("abc123").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("tom").password("abc123").roles("USER");
    }*/


}
