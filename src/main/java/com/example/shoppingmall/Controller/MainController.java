package com.example.shoppingmall.Controller;

import com.example.shoppingmall.Entity.UserEntity;
import com.example.shoppingmall.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final UserService userService;

    @GetMapping({"/", "/index"})
    public String home(HttpServletRequest request, Principal principal, Model model, String errorMessage)
            throws Exception {

        log.info("home() request={}", request);

        if (request != null && principal != null) {
            String email = principal.getName();
            log.info("home() email={}", email);

            UserEntity userEntity = userService.getLoginUser(request, principal);
            model.addAttribute("userEntity", userEntity);
        }

        model.addAttribute("errorMessage", errorMessage);

        return "index";
    }
}
