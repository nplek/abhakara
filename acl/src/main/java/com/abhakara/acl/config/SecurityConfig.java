package com.abhakara.acl.config;

import com.abhakara.acl.service.AbkUserDetailsService;
import com.abhakara.acl.service.UserDetailsServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(AclConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory
            .getLogger(SecurityConfig.class);

    @Autowired
    private AbkUserDetailsService calendarUserDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    //private CalendarUserDao calendarUserDao;

    @Autowired
    private DefaultMethodSecurityExpressionHandler expressionHandler;

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        //<authentication-provider user-service-ref="calendarUserDetailsService"/>
        auth
                .userDetailsService(calendarUserDetailsService)
                .passwordEncoder(passwordEncoder)
        ;
    }

    /**
     * The parent method from {@link WebSecurityConfigurerAdapter} (public UserDetailsService userDetailsService())
     * originally returns a {@link UserDetailsService}, but this needs to be a {@link UserDetailsManager}
     * UserDetailsManager vs UserDetailsService
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //return new AbkUserDetailsService(calendarUserDao);
        return userDetailsService;
    }

    /**
     * BCryptPasswordEncoder password encoder
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(4);
    }


    /**
     * HTTP Security configuration
     *
     * <pre><http auto-config="true"></pre> is equivalent to:
     * <pre>
     *  <http>
     *      <form-login />
     *      <http-basic />
     *      <logout />
     *  </http>
     * </pre>
     *
     * Which is equivalent to the following JavaConfig:
     *
     * <pre>
     *     http.formLogin()
     *          .and().httpBasic()
     *          .and().logout();
     * </pre>
     *
     * @param http HttpSecurity configuration.
     * @throws Exception Authentication configuration exception
     *
     * @see <a href="http://docs.spring.io/spring-security/site/migrate/current/3-to-4/html5/migrate-3-to-4-jc.html">
     *     Spring Security 3 to 4 migration</a>
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // Matching
        http.authorizeRequests()
//                .expressionHandler(expressionHandler)

                // FIXME: TODO: Allow anyone to use H2 (NOTE: NOT FOR PRODUCTION USE EVER !!! )
//                .antMatchers("/admin/h2/**").permitAll()

                .antMatchers("/").permitAll()
                .antMatchers("/login/*").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/signup/*").permitAll()
                .antMatchers("/errors/**").permitAll()
                .antMatchers("/admin/*").access("hasRole('ADMIN') and isFullyAuthenticated()")
                // NOTE: "/events/" is now protected by ACL:
//                .antMatchers("/events/").hasRole("ADMIN")
                .antMatchers("/**").hasRole("USER");

        // Login
        http.formLogin()
                .loginPage("/login/form")
                .loginProcessingUrl("/login")
                .failureUrl("/login/form?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/default", true)
                .permitAll();

        // Logout
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login/form?logout").deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .permitAll();

        // remember me configuration
        http.rememberMe().key("jbcpCalendar"); //.rememberMeParameter("_spring_security_remember_me");

        // Anonymous
        http.anonymous();

        // CSRF is enabled by default, with Java Config
        http.csrf().disable();

        // Exception Handling
        http.exceptionHandling()
//                .authenticationEntryPoint(forbiddenEntryPoint)
                .accessDeniedPage("/errors/403")
        ;

        // Enable <frameset> in order to use H2 web console
        http.headers().frameOptions().disable();
    }


    /**
     * This is the equivalent to:
     * <pre>
     *     <http pattern="/resources/**" security="none"/>
     *     <http pattern="/css/**" security="none"/>
     *     <http pattern="/webjars/**" security="none"/>
     * </pre>
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/css/**")
                .antMatchers("/webjars/**")
        ;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }

}