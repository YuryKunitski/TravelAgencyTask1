package by.epam.kunitski.travelagency.web.config;

import by.epam.kunitski.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.authorizeRequests().anyRequest().hasAnyRole("MEMBER", "ADMIN")
                .and()
                .authorizeRequests().antMatchers("/login**").permitAll()
                .and()
                .formLogin().loginPage("/login.html").loginProcessingUrl("/perform_login").defaultSuccessUrl("/home.html", true).permitAll()
                .failureUrl("/login.html?error=true")
                .and()
                .logout().logoutSuccessUrl("/home.html").permitAll()
                .and()
                .csrf().disable();



//        http.csrf()
//                .disable()
//                .authorizeRequests()
////                .antMatchers("/admin/**").hasRole("ADMIN")
////                .antMatchers("/anonymous*").anonymous()
////                .antMatchers("/login*").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/home.html", true)
//                .failureUrl("/login.html?error=true")               //<--------was hidden
////                .failureHandler(authenticationFailureHandler())
//                .and()
//                .logout()
//                .logoutUrl("/perform_logout")
//                .deleteCookies("JSESSIONID");
////                .logoutSuccessHandler(logoutSuccessHandler());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
