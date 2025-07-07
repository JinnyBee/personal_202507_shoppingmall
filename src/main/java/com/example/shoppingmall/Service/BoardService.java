package com.example.shoppingmall.Service;

import com.example.shoppingmall.DTO.BoardDTO;
import com.example.shoppingmall.DTO.GuestbookDTO;
import com.example.shoppingmall.Entity.BoardEntity;
import com.example.shoppingmall.Entity.GuestbookEntity;
import com.example.shoppingmall.Repository.BoardRepository;
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
public class BoardService {
    private final GuestbookRepository guestbookRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private final BoardRepository boardRepository;

   /* //방명록 등록
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
    }*/

    //방명록 조회
    public Page<BoardDTO> list(int page) throws Exception {

        int pageLimit = 10; //한 페이지에 출력되는 레코드 수

        Pageable pageable = PageRequest.of(page - 1, pageLimit, Sort.by(Sort.Direction.DESC, "id"));
        Page<BoardEntity> boardEntities;

        boardEntities = boardRepository.findAll(pageable);

        //각 조건에 따른 조회처리


        //결과 반환
        Page<BoardDTO> boardDTOS = boardEntities.map(data -> BoardDTO.builder()
                .id(data.getId())
                .userId(data.getUserEntity().getId())
                .userNickname(data.getUserEntity().getNickname())
                .title(data.getTitle())
                .content(data.getContent())
                .regDate(data.getRegDate())
                .modDate(data.getModDate())
                .build());

        return boardDTOS;
    }
}
