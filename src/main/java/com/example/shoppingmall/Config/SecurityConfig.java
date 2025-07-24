package com.example.shoppingmall.Config;

import com.example.shoppingmall.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    //로그인 성공,실패 시 동작할 커스텀 핸들러 주입
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Bean
    PasswordEncoder passwordEncoder(){
        //비밀번호 암호화를 위해 BCrypt 암호화기 등록
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        //인증에 필요한 AuthenticationManager 설정
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //보안 정책 설정 (URL 접근권한 설정 / 로그인 설정 /
        http
                .csrf(AbstractHttpConfigurer::disable)                      // CSRF 보호 기능을 비활성화 (실제 운영 환경에서는 활성화 권장)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index",
                                         "/user_login", "/user_login_error", "/user_join",
                                         "/css/**", "/js/**", "/assets/**",
                                         "/board/list").permitAll()
                        .requestMatchers("/user_logout").hasAnyRole("USER", "ADMIN")
                )
                .formLogin(form -> form
                        .loginPage("/user_login")                           //사용자정의 로그인 페이지
                        .loginProcessingUrl("/user_login")                  //로그인 폼의 action URL (POST 요쳥 경로)
                        .usernameParameter("email")                         //로그인 폼에서 사용자ID의 name 속성
                        .passwordParameter("password")                      //로그인 폼에서 비밀번호의 name 속성
                        //.failureUrl("user_login_error")  //로그인 실패 시 이동할 URL
                        .successHandler(customAuthenticationSuccessHandler) //로그인 성공 시 동작할 핸들러
                        .failureHandler(customAuthenticationFailureHandler) //로그인 실패 시 동작할 핸들러
                        .defaultSuccessUrl("/")                             //로그인 성공 후 이동할 기본 경로
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/user_logout")                          //로그아웃 요청 경로
                        .logoutSuccessHandler(customLogoutSuccessHandler)   //로그아웃 성공 시 동작할 핸들러
                        .logoutSuccessUrl("/")                              //로그아웃 성공 후 이동할 URL
                        .invalidateHttpSession(true)                        //세션 초기화
                        .deleteCookies("JSESSIONID", "remember-id") //로그아웃 후 쿠키 삭제
                );

        return http.build();
    }
}
