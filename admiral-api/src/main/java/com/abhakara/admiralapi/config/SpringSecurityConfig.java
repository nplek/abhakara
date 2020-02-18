package com.abhakara.admiralapi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("select email,password,enabled "
            + "from users "
            + "where email = ?")
        .authoritiesByUsernameQuery("select email,authority "
            + "from authorities "
            + "where email = ?");*/
            auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select email,password,enabled "
                + "from users "
                + "where email = ?")
            .passwordEncoder(passwordEncoder())
            .authoritiesByUsernameQuery("select u.email, r.name from users u left join users_roles ur on ur.user_id = u.id left join roles r on ur.role_id = r.id where u.email = ?");
    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/foos/**").permitAll()
                .antMatchers(HttpMethod.GET, "/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}