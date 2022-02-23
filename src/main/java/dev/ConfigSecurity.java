package dev;

import dev.auth.UtilisateurDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class ConfigSecurity
        extends WebSecurityConfigurerAdapter {

    /////////////////////////////////////////////////////
    @Autowired
    UserDetailsService utilisateurDetailsService;

    /**
     * Authentification manager
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(utilisateurDetailsService);
    }

    /**
     * A des fins de démonstration uniquement !
     * @return password encoder qui n'encode pas
     */
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /////////////////////////////////////////////////////

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        // TODO Auto-generated method stub
        /**
         * Attention je désactive SpringBootSecurity
         */

        http.csrf().disable().
                authorizeRequests().anyRequest().
                permitAll().and().httpBasic();        // SANS AUTHENTIFICATION
        //        authenticated().and().httpBasic();      // AVEC AUTHENTIFICATION BASIQUE (DEMO UNIQUEMENT)
        /**
         * Site inacessible :)
         * Pas de login rien :)))
         * http.csrf().disable().
         authorizeRequests().anyRequest().
         authenticated();
         */
		/*http.csrf().disable()
		.formLogin().
		loginProcessingUrl("/login").and()
		.logout().logoutUrl("/logout").
		invalidateHttpSession(true).and()
		.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/logout").permitAll()
		.anyRequest().authenticated().and().
		httpBasic();*/
        /**
         * httpBasic() -> API REST
         * avec leur propre sécurité
         */
    }
    //@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }
}