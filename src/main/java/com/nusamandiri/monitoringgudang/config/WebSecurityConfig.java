package com.nusamandiri.monitoringgudang.config;

import com.nusamandiri.monitoringgudang.services.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * @author galang
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Slf4j
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    @Qualifier("customSerDetailsService")
    private CustomUserDetailsService userDetailsService;

    private static final String SQL_ROLE
            = "select u.username, p.permission_value as authority "
            + "from c_security_user u "
            + "inner join c_security_role r on u.id_role = r.id "
            + "inner join c_security_role_permission rp on rp.id_role = r.id "
            + "inner join c_security_permission p on rp.id_permission = p.id "
            + "where u.username = ?";

    private static final String SQL_LOGIN
            = "select u.username as username,p.user_password as password, active "
            + "from c_security_user u "
            + "inner join c_security_user_password p on p.id_user = u.id "
            + "inner join c_security_role r on u.id_role = r.id "
            + "where username = ?";

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(this.dataSource);
        manager.setUsersByUsernameQuery(SQL_LOGIN);
        manager.setAuthoritiesByUsernameQuery(SQL_ROLE);
        return manager;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/login/**").permitAll()
                                .antMatchers("/assets/**").permitAll()
                                .antMatchers("/forgot_password/**").permitAll()
                                .antMatchers("/index/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
        return http.build();
    }

}

