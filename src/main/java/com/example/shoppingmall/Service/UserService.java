package com.example.shoppingmall.Service;

import com.example.shoppingmall.Constant.UserRole;
import com.example.shoppingmall.DTO.UserDTO;
import com.example.shoppingmall.Entity.UserEntity;
import com.example.shoppingmall.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //로그인 시 사용자의 ID(email)를 받아옴
        //사용자 테이블에서 email로 사용자 검색
        UserEntity userEntity = userRepository
                .findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("사용자 없음: " + email));

        return User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword())
                .roles(userEntity.getRole().toString())
                .build();
    }

    //회원가입
    public void registerUser(UserDTO userDTO) {

        log.info("registerUser() userDTO: {}", userDTO);

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(userDTO.getEmail());
        userEntity.setNickname(userDTO.getNickname());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setRole(UserRole.USER);

        userRepository.save(userEntity);
    }

    //회원정보 조회
    public UserEntity getLoginUser(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("해당 이메일의 사용자를 찾을 수 없습니다: " + email));
    }

    public UserEntity getLoginUser(HttpServletRequest request, Principal principal) throws Exception {

        HttpSession session = request.getSession();
        UserEntity userEntity = (UserEntity) session.getAttribute("user");

        if (userEntity != null) {
            return userEntity;
        } else {
            String email = principal.getName();
            userEntity = userRepository.findByEmail(email).orElseThrow();
            return userEntity;
        }
    }
}
