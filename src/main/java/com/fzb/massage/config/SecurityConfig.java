package com.fzb.massage.config;
/*
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页所有人可访问  功能页对有权限人可访问
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/worker/**").hasRole("worker")
                .antMatchers("/admin/**").hasRole("admin");

        // 没有权限  默认tiao跳到登录页
//        http.formLogin().loginPage("/login")
//                .loginProcessingUrl("login")
//                .usernameParameter("username");
        http.formLogin();

        // 防止网站攻击
        // http.csrf().disable();
        // 注销
        http.logout().logoutSuccessUrl("/");

        // 记住我功能
        http.rememberMe();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("zhangbo").password(new BCryptPasswordEncoder().encode("123")).roles("worker")
                .and().withUser("root").password(new BCryptPasswordEncoder().encode("123")).roles("worker", "admin");
    }
}
*/