package com.example.shoppingmall.Service;

import com.example.shoppingmall.DTO.GuestbookDTO;
import com.example.shoppingmall.Entity.GuestbookEntity;
import com.example.shoppingmall.Repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GuestbookService {
    private final GuestbookRepository guestbookRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    //방명록 등록
    public void register(GuestbookDTO guestbookDTO) throws Exception {

        GuestbookEntity guestbookEntity = modelMapper.map(guestbookDTO, GuestbookEntity.class);
        guestbookRepository.save(guestbookEntity);
    }

    //방명록 수정
    public void modify(GuestbookDTO guestbookDTO) throws Exception {

        int gno = guestbookDTO.getGno();

        Optional<GuestbookEntity> guestbookEntity = guestbookRepository.findById(gno);
        GuestbookEntity data = guestbookEntity.orElseThrow();

        GuestbookEntity update = modelMapper.map(guestbookDTO, GuestbookEntity.class);
        update.setGno(data.getGno());
        update.setTitle(guestbookDTO.getTitle());
        update.setContent(guestbookDTO.getContent());
        update.setWriter(guestbookDTO.getWriter());

        guestbookRepository.save(update);
    }

    //방명록 삭제
    public void remove(int gno) throws Exception {

        guestbookRepository.deleteById(gno);
    }

    //방명록 상세조회
    public GuestbookDTO read(int gno) throws Exception {
        Optional<GuestbookEntity> guestbookEntity = guestbookRepository.findById(gno);

        //변환
        GuestbookDTO guestbookDTO = modelMapper.map(guestbookEntity, GuestbookDTO.class);
        return guestbookDTO;
    }

    //방명록 조회
    public Page<GuestbookDTO> list(int page, String type, String keyword) throws Exception {

        int pageLimit = 10; //한 페이지에 출력되는 레코드 수

        Pageable pageable = PageRequest.of(page - 1, pageLimit, Sort.by(Sort.Direction.DESC, "gno"));
        Page<GuestbookEntity> guestbookEntities;

        guestbookEntities = guestbookRepository.findAll(pageable);

        //각 조건에 따른 조회처리
        if (type.equals("t") && keyword != null) {
            guestbookEntities = guestbookRepository.searchTitle(keyword, pageable);

        } else if (type.equals("c") && keyword != null) {
            guestbookEntities = guestbookRepository.searchContent(keyword, pageable);

        } else if (type.equals("w") && keyword != null) {
            guestbookEntities = guestbookRepository.searchWriter(keyword, pageable);

        } else if (type.equals("tc") && keyword != null) {
            guestbookEntities = guestbookRepository.searchTitleContent(keyword, pageable);

        } else if (type.equals("tcw") && keyword != null) {
            guestbookEntities = guestbookRepository.searchTitleContentWriter(keyword, pageable);

        } else {
            guestbookEntities = guestbookRepository.findAll(pageable);

        }

        //결과 반환
        Page<GuestbookDTO> guestbookDTOS = guestbookEntities.map(data->GuestbookDTO.builder()
                .gno(data.getGno())
                .title(data.getTitle())
                .content(data.getContent())
                .writer(data.getWriter())
                .regDate(data.getRegDate())
                .modDate(data.getModDate())
                .build());

        return guestbookDTOS;
    }
}
