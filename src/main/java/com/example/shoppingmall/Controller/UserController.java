package com.example.shoppingmall.Controller;

import com.example.shoppingmall.DTO.UserDTO;
import com.example.shoppingmall.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    //회원가입 Form
    @GetMapping("/user_join")
    public String joinForm() throws Exception {

        return "user/join";
    }

    //회원가입 Proc
    @PostMapping("/user_join")
    public String joinProc(@Valid UserDTO userDTO, BindingResult bindingResult) throws Exception {

        log.info("joinProc() userDTO: {}", userDTO);

        if (bindingResult.hasErrors()) {
            log.error("bindingResult has Errors. {}", bindingResult);
            return "user/join";
        }

        try {
            userService.registerUser(userDTO);

        } catch (Exception e) {
            return "user/join";
        }

        return "redirect:/user_login";
    }

    //로그인 Form
    @GetMapping("/user_login")
    public String loginForm(HttpServletRequest request, Model model) throws Exception {

        log.info("loginForm()");
        log.info("Request URI: {}", request.getRequestURI());

        return "user/login";
    }

    //로그인오류 Form
    @RequestMapping("/user_login_error")
    public String loginError(HttpServletRequest request, Model model) throws Exception {

        log.info("loginError()");

        //Object errorMessage = request.getSession().getAttribute("errorMessage");
        Object errorMessage = request.getAttribute("errorMessage");
        if (errorMessage != null) {
            log.info("errorMessage={}", errorMessage);
            model.addAttribute("errorMessage", errorMessage);
        }
        //else{
        //    model.addAttribute("errorMessage","계정이 정지되었습니다.\\n자세한 사항은 관리자에게 문의하세요.\\nOOOOO@gmail.com");
        //}

        return "user/login";
    }
}
