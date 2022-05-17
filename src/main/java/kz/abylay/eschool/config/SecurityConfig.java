package kz.abylay.eschool.config;

import kz.abylay.eschool.models.Users;
import kz.abylay.eschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().accessDeniedPage("/errorpage/403");

        http.authorizeRequests().antMatchers("/", "/css/**", "/js/**").permitAll();

        http.formLogin().loginPage("/login") // /login, login.html
                .usernameParameter("user_email") // <input name="user_email" type = "email">
                .passwordParameter("user_password") // <input name="user_password" type = "password">
                .loginProcessingUrl("/auth") // <form th:action="@{'/auth'} method=post>"
                .failureUrl("/login?error")
                .defaultSuccessUrl("/profile");

        http.logout()
                .logoutUrl("/logout") // <form th:action="@{'/logout'} method=post>
                .logoutSuccessUrl("/login").permitAll();

    }
}
