package com.beautiful.stay.controller;

import com.beautiful.stay.domain.NoteVO;
import com.beautiful.stay.service.NoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/note/*")
public class NoteController {

    @Autowired
    NoteService noteService;

    private Logger logger = LogManager.getLogger(NoteController.class);

    @GetMapping(value = "/create")
    public String noteCreateGET(NoteVO vo, HttpSession session, RedirectAttributes rttr, Model model) throws Exception{
        logger.info("*** 컨트롤러 : noteCreate - GET");
        logger.info("*** 컨트롤러 : 노트 작성 폼에 접근했습니다.");

        // 로그인 한 유저만 글 쓸 수 있도록 처리
        String name = (String)session.getAttribute("name");
        logger.info("*** 세션에서 넘어온 닉네임:"+name);

        if(name==null){
            rttr.addFlashAttribute("msg","nameNull");
            return "redirect:/note/list";
        }

        // 세션에서 넘어온 닉네임 뷰페이지에도 전달
        model.addAttribute("name", name);

        return "/note/create";
    }

    @PostMapping(value="/create")
    public String noteCreatePOST(NoteVO vo) throws Exception{
        logger.info("*** 컨트롤러 : noteCreatePOST 접근");
        logger.info("*** 넘어온 글 정보:"+vo);
        noteService.create(vo);
        logger.info("*** 글 등록을 완료했습니다.");
        return "redirect:/note/list";
    }

    // 글 목록보기
    @GetMapping(value="/list")
    void noteListGET(Model model)throws Exception{
        logger.info("***글 목록을 조회합니다.");

        List<NoteVO> list = null;
        list = noteService.listAll();
        model.addAttribute("list", list);
    }

    // 글 수정하기
    @GetMapping(value = "/modify")
    public String noteModifyGET(@RequestParam("bno") int bno, Model model) throws Exception{
        logger.info("*** "+bno+"번 게시글을 수정합니다.");
        NoteVO vo = noteService.read(bno);
        model.addAttribute("note", vo);
        return "/note/modify";
    }

    @PostMapping(value="/modify")
    public String noteModifyPOST(NoteVO vo) throws Exception{
        noteService.modify(vo);
        logger.info("*** 게시글을 성공적으로 수정했습니다.");
        return "redirect:/note/list";
    }

    // 글 삭제하기
    @GetMapping(value="delete")
    public String noteModifyGET(int bno) throws Exception{
        logger.info("*** "+bno+"번 게시글을 삭제합니다.");
        noteService.delete(bno);
        logger.info("*** "+bno+"번 게시글 삭제를 완료했습니다.");
        return "redirect:/note/list";
    }


}
