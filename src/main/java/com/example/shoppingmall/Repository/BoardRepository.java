package com.example.shoppingmall.Repository;

import com.example.shoppingmall.Entity.BoardEntity;
import com.example.shoppingmall.Entity.GuestbookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    Page<BoardEntity> findAll(Pageable pageable);
}
