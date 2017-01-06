package com.aimslabs.config;

import com.aimslabs.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by sayemkcn on 1/5/17.
 */
@EnableWebSecurity
public class SecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**","/fonts/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/**","/","/login","/digits-login","/logout","/register","/profile/create","/fonts/**").permitAll()
//                    .antMatchers("/child/**").hasAnyRole("PARENTS","AUTISM_CENTER","SUPER_ADMIN")
                    .antMatchers("/center/**").hasAnyRole("AUTISM_CENTER")
//                    .antMatchers("/users/*/roles").hasRole("SUPER_ADMIN")
                    .antMatchers("/users/**").hasAnyRole("ADMIN","SUPER_ADMIN")
                    .antMatchers("/admin/questions").hasRole("SUPER_ADMIN")
                    .antMatchers("/admin/**").hasAnyRole("ADMIN","SUPER_ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .failureUrl("/login?error")
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
//                .inMemoryAuthentication()
//                    .withUser("admin").password("password").roles("USER");
                .userDetailsService(this.customUserDetailsService);
    }
}
