package com.example.shoppingmall.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class GuestbookDTO {
    private int gno;        //번호

    @NotBlank(message = "제목은 생략이 불가능합니다.")
    private String title;   //제목

    private String content; //내용

    @NotBlank(message = "작성자는 생략이 불가능합니다.")
    private String writer;  //작성자

    private LocalDateTime regDate;  //생성날짜
    private LocalDateTime modDate;  //수정날짜
}