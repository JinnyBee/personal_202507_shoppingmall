package com.example.shoppingmall.Config;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        //onAuthenticationFailure: 로그인 실패 시 자동으로 호출되는 메서도
        //exception: 로그인 실패 원인이 담긴 예외 객체
        log.info("onAuthenticationFailure() 아이디나 비밀번호가 다릅니다");
        log.info("onAuthenticationFailure() exception={}", exception.getMessage());

        request.setAttribute("errorMessage","로그인 실패!!! 아이디나 비밀번호가 다릅니다.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user_login_error");
        dispatcher.forward(request, response);
    }
}
