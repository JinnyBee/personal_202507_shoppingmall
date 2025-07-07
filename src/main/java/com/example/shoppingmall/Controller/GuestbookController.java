package com.example.shoppingmall.Controller;

import com.example.shoppingmall.DTO.GuestbookDTO;
import com.example.shoppingmall.Service.GuestbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService guestbookService;

    //방명록 목록
    @GetMapping(value = {"/guestbook/list"})
    public String listForm(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "type", defaultValue = "") String type,
                           @RequestParam(value = "keyword", defaultValue = "") String keyword,
                           Model model) throws Exception {

        Page<GuestbookDTO> guestbookDTOS = guestbookService.list(page, type, keyword);

        //페이지처리
        int blockLimit = 10; //한번에 출력할 페이지 번호 개수
        int startPage = (((int)(Math.ceil((double)page/blockLimit)))-1) * blockLimit + 1;
        int endPage = ((startPage+blockLimit-1)<guestbookDTOS.getTotalPages()) ? startPage+blockLimit-1 : guestbookDTOS.getTotalPages();

        System.out.println("listForm> page: " + page);
        System.out.println("listForm> type: " + type);
        System.out.println("listForm> keyword: " + keyword);
        System.out.println("listForm> totalElements: " + guestbookDTOS.getTotalElements() + ", totalPages: " + guestbookDTOS.getTotalPages());
        System.out.println("listForm> startPage: " + startPage);
        System.out.println("listForm> endPage: " + endPage);

        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);
        model.addAttribute("startPage", startPage);     //시작페이지
        model.addAttribute("endPage", endPage);         //종료페이지
        model.addAttribute("lists", guestbookDTOS);     //데이터

        return "guestbook/list";
    }

    //방명록 등록폼
    @GetMapping("/guestbook/register")
    public String registerForm(Model model) throws Exception {
        return "guestbook/register";
    }

    //방명록 등록처리
    @PostMapping("/guestbook/register")
    public String registerProc(GuestbookDTO guestbookDTO, Model model) throws Exception {

        System.out.println("registerProc> title: " + guestbookDTO.getTitle());
        System.out.println("registerProc> content: " + guestbookDTO.getContent());
        System.out.println("registerProc> writer: " + guestbookDTO.getWriter());

        guestbookService.register(guestbookDTO);

        return "redirect:/guestbook/list";
    }

    //방명록 수정폼
    @GetMapping("/guestbook/modify")
    public String modifyForm(int gno, Model model) throws Exception {

        System.out.println("modifyForm> gno: " + gno);

        GuestbookDTO guestbookDTO = guestbookService.read(gno);
        model.addAttribute("data", guestbookDTO);

        return "guestbook/modify";
    }

    //방명록 수정처리
    @PostMapping("/guestbook/modify")
    public String modifyProc(GuestbookDTO guestbookDTO, Model model) throws Exception {

        System.out.println("modifyProc> gno: " + guestbookDTO.getGno());
        System.out.println("modifyProc> title: " + guestbookDTO.getTitle());
        System.out.println("modifyProc> content: " + guestbookDTO.getContent());
        System.out.println("modifyProc> writer: " + guestbookDTO.getWriter());

        guestbookService.modify(guestbookDTO);

        return "redirect:/guestbook/list";
    }

    //방명록 삭제처리
    @GetMapping("/guestbook/remove")
    public String removeProc(int gno, Model model) throws Exception {

        System.out.println("removeProc> gno: " + gno);
        guestbookService.remove(gno);

        return "redirect:/guestbook/list";
    }

    //방명록 상세보기
    @GetMapping("/guestbook/read")
    public String readForm(int gno, Model model) throws Exception {


        GuestbookDTO guestbookDTO = guestbookService.read(gno);
        model.addAttribute("data", guestbookDTO);

        System.out.println("readForm> gno: " + gno);
        System.out.println("readForm> title: " + guestbookDTO.getTitle());
        System.out.println("readForm> content: " + guestbookDTO.getContent());
        System.out.println("readForm> writer: " + guestbookDTO.getWriter());


        return "guestbook/read";
    }
}
