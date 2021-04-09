package com.example.demo.config;

import com.example.demo.services.implementation.UserSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserSecurity userSecurity;
    private final UserAuthenticationLogoutSuccessHandler userAuthenticationLogoutSuccessHandler;

    public AppSecurityConfig(PasswordEncoder passwordEncoder, UserSecurity userSecurity, UserAuthenticationLogoutSuccessHandler userAuthenticationLogoutSuccessHandler) {
        this.passwordEncoder = passwordEncoder;
        this.userSecurity = userSecurity;

        this.userAuthenticationLogoutSuccessHandler = userAuthenticationLogoutSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/js/**",
                "/css/**", "/img/**").permitAll()
                .antMatchers("/", "/users/login", "/users/register").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin().loginPage("/users/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
                .failureForwardUrl("/users/login-error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .addLogoutHandler(userAuthenticationLogoutSuccessHandler)
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userSecurity)
                .passwordEncoder(passwordEncoder);
    }
}
