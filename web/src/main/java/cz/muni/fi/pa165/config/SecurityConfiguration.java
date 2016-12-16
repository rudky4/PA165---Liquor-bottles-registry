package cz.muni.fi.pa165.config;

    import cz.muni.fi.pa165.enums.PersonRole;
import cz.muni.fi.pa165.security.CustomAuthenticationProvider;
import cz.muni.fi.pa165.security.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.inject.Inject;

/**
 * @author mhajas
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = {CustomAuthenticationProvider.class})
@Import(RestConfiguration.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Inject
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Inject
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/rest/lab/bottles").hasRole("LAB")
                .antMatchers(HttpMethod.GET, "/rest/store/*/bottles/all").hasRole("POLICE")
                .antMatchers(HttpMethod.GET, "/rest/store").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/store/*").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/store/*/bottles").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/store/bottleType/*").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/manufacturer").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/manufacturer/*").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/manufacturer/*/bottleTypes").permitAll()
                .antMatchers(HttpMethod.GET, "/rest/bottleType").permitAll()
                .antMatchers(HttpMethod.PUT, "/rest/bottleType/*").hasRole(PersonRole.MANUFACTURER.name())
                .antMatchers(HttpMethod.PUT, "/rest/bottle/*/toxicity/*").hasRole(PersonRole.LAB.name())
                .antMatchers(HttpMethod.POST, "/rest/bottleType/*").hasRole(PersonRole.MANUFACTURER.name())
                .antMatchers(HttpMethod.POST, "/rest/bottle").hasRole(PersonRole.STORE_OWNER.name())
                .antMatchers(HttpMethod.DELETE, "/rest/bottle/*").hasRole(PersonRole.STORE_OWNER.name())
                .antMatchers("/rest/user").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/partials/**").permitAll()
                .antMatchers("/index.html").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login.html").permitAll().and()
                .logout().logoutUrl("/logout.html").logoutSuccessUrl("/index.html?logout").permitAll().and().csrf().disable();
    }

}
