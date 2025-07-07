package com.example.shoppingmall.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Builder

@Table(name = "guest")
public class GuestbookEntity extends BaseEntity {
    @Id
    @Column(name = "gno")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gno;        //번호

    @Column(name = "title", length = 100, nullable = false)
    private String title;   //제목

    @Column(name = "content", length = 255)
    private String content; //내용

    @Column(name = "writer", length = 20)
    private String writer;  //작성자
}
