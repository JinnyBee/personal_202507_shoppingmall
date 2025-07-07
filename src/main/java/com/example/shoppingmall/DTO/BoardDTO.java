package com.example.shoppingmall.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {

    private Integer id;             //게시판 id
    private Integer userId;         //게시글 작성자 id (of users table)
    private String userNickname;    //게시글 작성자 nickname (of users table)

    @NotEmpty(message = "제목을 적어주세요.")
    private String title;           //게시글 제목

    @NotEmpty(message = "내용을 적어주세요.")
    @Size(min = 1, max = 5000)
    private String content;         //게시글 내용

    private LocalDateTime regDate;  //생성일
    private LocalDateTime modDate;  //수정일
}