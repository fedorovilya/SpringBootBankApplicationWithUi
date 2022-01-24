package com.example.courseProject.securuty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        //выдача прав. antMatchers - доступ к ресурсам.
        http.authorizeRequests().antMatchers("/clients").hasAuthority("ADMIN")
                .antMatchers("/clients/**").authenticated()
                .antMatchers("/signUp").permitAll()
                .antMatchers("/transactions").hasAuthority("ADMIN")
                .antMatchers("/cards").hasAuthority("ADMIN")
                .and()
                .formLogin()
                .loginPage("/signIn")
                .defaultSuccessUrl("/homepage" )
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();
    }
}
