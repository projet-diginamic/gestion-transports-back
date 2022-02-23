package dev;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class ConfigSecurity
        extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        // TODO Auto-generated method stub
        /**
         * Attention je désactive SpringBootSecurity
         */

        http.csrf().disable().
                authorizeRequests().anyRequest().
                permitAll().and().httpBasic();
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