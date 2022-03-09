package com.spring.security.config;

import com.spring.security.filter.CustomSecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * This class will be used for  customizing the spring security.Earlier we had only added spring security
 * dependency as part of pom.xml due to that using spring security generated password and username as
 * user we can access endpoint with Basic Authentication.
 */

/**
 * If we don't configure and provide PasswordEncoder even Basic Authentication will also not work.
 * Application will up normally but when we hit any endpoint we will run into exception.
 */
@Configuration
public class CustomAuthenticationManager extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    /**
     * Till now we have customized userdetailsService and PasswordEncoder for our application.
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//        UserDetails user = User.withUsername("Deepak").password(passwordEncoder.encode("Deepak")).authorities("read").build();
//        userDetailsService.createUser(user);
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//        }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.authenticationProvider(customAuthenticationProvider);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * http.httpBasic() is used for basic authentication.
         */

       http.httpBasic();
       // http.formLogin();
       http.authorizeRequests().antMatchers("/hello").authenticated();
        /**
         * Here we are attaching our custom filter into Spring security filter chain.
         */
       http.addFilterBefore(new CustomSecurityFilter(), BasicAuthenticationFilter.class);
    }


}
