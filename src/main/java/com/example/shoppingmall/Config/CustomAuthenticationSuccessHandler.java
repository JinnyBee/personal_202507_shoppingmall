package com.example.shoppingmall.Config;

import com.example.shoppingmall.Entity.UserEntity;
import com.example.shoppingmall.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        log.info("onAuthenticationSuccess() authentication's name={}", authentication.getName());

        String email = authentication.getName();
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow();

        //String role = userEntity.getRole().name();
        //if ("ADMIN".equals(role)) {
        //    response.sendRedirect("/");
        //    return;
        //} else if ("USER".equals(role)) {
        //    response.sendRedirect("/");
        //    return;
        //}

        //response.sendRedirect("/");
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null){
            redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
        } else {
            redirectStrategy.sendRedirect(request, response, "/");
        }
    }
}