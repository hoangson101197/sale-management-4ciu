package com.codegym.fourciumanagerprocesssale.config;

import com.codegym.fourciumanagerprocesssale.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // Xác định lớp WebSecurityConfig của ta là một lớp dùng cấu hình
@EnableWebSecurity // Kích hoạt việc tích hợp SpringSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    // Cấu hình chi tiết về bảo mật
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                // permitAll() cho phép các user đều được truy cập
                .authorizeRequests()
                .antMatchers("/registration").permitAll()
                // hasRole(roleName) Chỉ cho phép các user có GrantedAuthority là Role_roleName mới được phép truy cập
                .antMatchers("//**").hasRole("MEMBER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login").successHandler(customizeAuthenticationSuccessHandler)
                // Đường dẫn tới form đăng nhập
                // giá trị của thuộc tính name của 2 input nhập username và password
                .usernameParameter("email")
                .passwordParameter("password")
                .failureUrl("/login?error")
                .and()
                .rememberMe()
                .key("rem-me-key")
                // Tên của hộp kiểm tra tại trang đăng nhập
                .rememberMeParameter("remember")
                // Tên của cookie
                .rememberMeCookieName("rememberlogin")
                .tokenValiditySeconds(100)
                .and()
                // Khi người dùng không đủ quyền để truy cập vào một trang, ta sẽ redirect người dùng về một trang 403 nào đó:
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .logout().logoutSuccessUrl("/login").logoutUrl("/logout").permitAll();

    }
}