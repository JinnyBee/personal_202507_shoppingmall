package com.example.shoppingmall;

import com.example.shoppingmall.DTO.GuestbookDTO;
import com.example.shoppingmall.Service.GuestbookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

@SpringBootTest
public class GuestbookServiceTest {
    @Autowired
    private GuestbookService guestbookService;

    @Test
    public void 방명록_등록() throws Exception {
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("새로운 데이터")
                .content("새로운 내용")
                .writer("작은별")
                .build();

        guestbookService.register(guestbookDTO);
    }

    @Test
    public void 방명록_수정() throws Exception {
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .gno(4)
                .title("수정 데이터")
                .content("수정 내용")
                .writer("이은슈")
                .build();
        guestbookService.modify(guestbookDTO);
    }

    @Test
    public void 방명록_삭제() throws Exception {
        guestbookService.remove(10);
    }

    @Test
    public void 방명록_조회() throws Exception {

        int page = 1;
        String type = "t";
        String keyword = "첫";

        Page<GuestbookDTO> guestbookDTOS = guestbookService.list(page, type, keyword);
        System.out.println("총 페이지 : " + guestbookDTOS.getTotalPages());
    }
}
