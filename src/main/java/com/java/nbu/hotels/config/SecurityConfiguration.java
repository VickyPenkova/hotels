package com.java.nbu.hotels.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
   @Autowired
   UserDetailsService userDetailsService;

   @Autowired
   private AuthenticationSuccessHandler authenticationSuccessHandler;

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService)
            .passwordEncoder(getPasswordEncoder());
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
            .antMatchers("/register").permitAll()
            .antMatchers("/confirm").permitAll();

      http
            .csrf().disable();
      http
            .authorizeRequests()
               .antMatchers("/resources/**").permitAll()
               .antMatchers("/admin/**").access("hasRole('ADMIN')")
               .antMatchers("/user/**").access("hasRole('USER')") // can pass multiple roles
               .anyRequest().authenticated()
               .and()
            .formLogin()
               .loginPage("/login")
               .successHandler(authenticationSuccessHandler)
               .permitAll()
               .failureUrl("/login?error=true")
               //.defaultSuccessUrl("/user/dashboard", true)
               .and()
            .logout()
               .permitAll()
               .invalidateHttpSession(true)
               .logoutSuccessUrl("/login?logout=true");
   }

   private PasswordEncoder getPasswordEncoder() {
      return new PasswordEncoder() {
         @Override
         public String encode(CharSequence charSequence) {
            return charSequence.toString();
         }

         @Override
         public boolean matches(CharSequence rawPassword, String hashedPassword) {
//            String hashedPassword2 = new BCryptPasswordEncoder().encode(rawPassword); // hash your rawPassword here
//            return hashedPassword2.equals(hashedPassword);
            return rawPassword.toString().equals(hashedPassword);
         }
      };
   }

   // For admin page
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
      authenticationMgr
            .inMemoryAuthentication()
            .withUser("admin").password("passadmin").roles("ADMIN");
   }
}
