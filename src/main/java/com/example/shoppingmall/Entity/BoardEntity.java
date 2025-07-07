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
@SequenceGenerator(
        name = "board_SEQ",
        sequenceName = "board_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Table(name = "board")
public class BoardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "board_SEQ")
    private Integer id;     //게시판 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "content", length = 5000, nullable = false)
    private String content;
}
