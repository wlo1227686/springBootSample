package com.chengfu.springBootSample.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    
    
    
    /**
     * 設定安全過濾鏈<br>
     * 1. 禁用了 CSRF 防護<br>
     * 2. JWT 驗證過濾器<br>
     * 
     * @param http HttpSecurity
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        // 禁用了 CSRF 防護
//        http.csrf (csrf -> csrf.disable ());
        
        return http.build ();
    }
    
    
    /**
     * 設定認證管理器
     * 
     * @param configuartion AuthenticationConfiguration
     * @return
     * @throws Exception
     */
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuartion) throws Exception {
        
        return configuartion.getAuthenticationManager ();
    }
}
