package com.example.shoppingmall.Repository;

import com.example.shoppingmall.Entity.GuestbookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface GuestbookRepository extends JpaRepository<GuestbookEntity, Integer> {

    //제목으로 검색시 페이지처리
    @Query("SELECT u FROM GuestbookEntity u WHERE u.title like %:keyword%")
    Page<GuestbookEntity> searchTitle(String keyword, Pageable pageable);

    //내용으로 검색시 페이지처리
    @Query("SELECT u FROM GuestbookEntity u WHERE u.content like %:keyword%")
    Page<GuestbookEntity> searchContent(String keyword, Pageable pageable);

    //작성자로 검색시 페이지처리
    @Query("SELECT u FROM GuestbookEntity u WHERE u.writer like %:keyword%")
    Page<GuestbookEntity> searchWriter(String keyword, Pageable pageable);

    //제목+내용으로 검색시 페이지처리
    @Query("SELECT u FROM GuestbookEntity u WHERE u.title like %:keyword% or u.content like %:keyword%")
    Page<GuestbookEntity> searchTitleContent(String keyword, Pageable pageable);

    //제목+내용+작성자로 검색시 페이지처리
    @Query("SELECT u FROM GuestbookEntity u WHERE u.title like %:keyword% or u.content like %:keyword% or u.writer like %:keyword%")
    Page<GuestbookEntity> searchTitleContentWriter(String keyword, Pageable pageable);
}
