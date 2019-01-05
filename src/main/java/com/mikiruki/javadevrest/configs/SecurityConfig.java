//package com.mikiruki.javadevrest.configs;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@EnableAutoConfiguration
//@ComponentScan
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
//
//    @Autowired
//    private MySavedRequestAwareAuthenticationSuccessHandler mySavedRequestAwareAuthenticationSuccessHandler;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("{noop}admnpass")
//                .roles("MODERATOR")
//                .and()
//                .withUser("user")
//                .password("{noop}usrpass")
//                .roles("USER");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(restAuthenticationEntryPoint)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/courses", "/students").hasAnyRole()
//                .and()
//                .formLogin()
//                .successHandler(mySavedRequestAwareAuthenticationSuccessHandler)
//                .and()
//                .headers().frameOptions().disable()
//                .and()
//                .logout();
//    }
//}
