package com.java.nbu.hotels.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
   @Autowired
   UserDetailsService userDetailsService;

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService)
            .passwordEncoder(getPasswordEncoder());

      //auth.authenticationProvider(authenticationProvider());
   }

   @Bean
   public DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider
            = new DaoAuthenticationProvider();
      authProvider.setUserDetailsService(userDetailsService);
      authProvider.setPasswordEncoder(encoder());
      return authProvider;
   }

   @Bean
   public PasswordEncoder encoder() {
      return new BCryptPasswordEncoder(11);
   }


   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
            .antMatchers("/register").permitAll()
            .antMatchers("/confirm").permitAll();

      http.csrf().disable();
      http.authorizeRequests()
            //.antMatchers("/hello").authenticated()
            .anyRequest().permitAll()
            .and().formLogin().permitAll()
            .and().formLogin().defaultSuccessUrl("/user/index.html", true);
   }

   private PasswordEncoder getPasswordEncoder() {
      return new PasswordEncoder() {
         @Override
         public String encode(CharSequence charSequence) {
            return charSequence.toString();
         }

         @Override
         public boolean matches(CharSequence charSequence, String s) {
            return true;
         }
      };
   }
}
