package com.university.accommodationmanager.config;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.university.accommodationmanager.service.impl.UserDetailsServiceImpl;
//@Configuration
//@EnableConfigurationProperties
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//  @Autowired
//  UserDetailsServiceImpl userDetailsService;
//
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http
//      .csrf().disable()
//            .antMatcher("/**")
//      .authorizeRequests()
//            .antMatchers("/auth").permitAll()
//            .anyRequest().authenticated()
//      .and().httpBasic()
//      .and().sessionManagement().disable();
//  }
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//   return NoOpPasswordEncoder.getInstance();
//  }
//
//  @Override
//  public void configure(AuthenticationManagerBuilder builder) throws Exception {
//   builder.userDetailsService(userDetailsService);
//  }
//}


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
      @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
      .csrf().disable()
            .antMatcher("/**")
      .authorizeRequests()
            .antMatchers("/auth").permitAll()
                .anyRequest().authenticated().and().oauth2Login();
        }

}