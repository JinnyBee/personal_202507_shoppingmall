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
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        log.info("onAuthenticationFailure() 아이디나 비밀번호가 다릅니다");

        request.setAttribute("errorMessage","아이디나 비밀번호가 다릅니다.");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user_login_error");
        dispatcher.forward(request, response);

        //request.getSession().setAttribute("errorMessage", "아이디나 비밀번호가 다릅니다.");
        //response.sendRedirect("/user_login");
        //response.sendRedirect("/");
    }
}
