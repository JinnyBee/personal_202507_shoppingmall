package com.example.shoppingmall.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Column(name="reg_Date", nullable=false, updatable=false)
    @CreatedDate
    private LocalDateTime regDate; //생성날짜

    @Column(name="mod_Date")
    @LastModifiedDate
    private LocalDateTime modDate; //수정날짜
}
