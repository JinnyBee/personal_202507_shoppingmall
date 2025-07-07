package com.example.shoppingmall;

import com.example.shoppingmall.Entity.GuestbookEntity;
import com.example.shoppingmall.Repository.GuestbookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootTest
public class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void 테이블_저장() {
        for(int i=1; i<=50; i++) {
            GuestbookEntity guestbookEntity = GuestbookEntity.builder()
                    .title("첫 데이터" + (i%5))
                    .content("첫 내용" + (i%5))
                    .writer("홍길동" + (i%5))
                    .build();
            guestbookRepository.save(guestbookEntity);
        }
    }

    @Test
    public void 페이징() {
        Page<GuestbookEntity> guestbookEntities = guestbookRepository
                .findAll(PageRequest.of(1, 20, Sort.by(Sort.Direction.DESC, "gno")));
        System.out.println("총 페이지 수 : " + guestbookEntities.getTotalPages());
    }

    @Test
    public void 제목으로_검색() {
        Page<GuestbookEntity> guestbookEntities = guestbookRepository
                .searchTitle("첫", PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "gno")));
        System.out.println("총 페이지 수 : " + guestbookEntities.getTotalPages());
    }

    @Test
    public void 내용으로_검색() {
        Page<GuestbookEntity> guestbookEntities = guestbookRepository
                .searchContent("첫", PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "gno")));
        System.out.println("총 페이지 수 : " + guestbookEntities.getTotalPages());
    }

    @Test
    public void 작성자로_검색() {
        Page<GuestbookEntity> guestbookEntities = guestbookRepository
                .searchWriter("길동", PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "gno")));
        System.out.println("총 페이지 수 : " + guestbookEntities.getTotalPages());
    }

    @Test
    public void 제목_내용으로_검색() {
        Page<GuestbookEntity> guestbookEntities = guestbookRepository
                .searchTitleContent("첫", PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "gno")));
        System.out.println("총 페이지 수 : " + guestbookEntities.getTotalPages());
    }

    @Test
    public void 제목_내용_작성자로_검색() {
        Page<GuestbookEntity> guestbookEntities = guestbookRepository
                .searchTitleContentWriter("첫", PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "gno")));
        System.out.println("총 페이지 수 : " + guestbookEntities.getTotalPages());
    }
}
