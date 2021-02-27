package com.beautiful.stay.controller;

import com.beautiful.stay.domain.NoteVO;
import com.beautiful.stay.domain.PageVO;
import com.beautiful.stay.domain.Pagination;
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
            return "redirect:/user/login";
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

    // 글 목록보기 + 페이징 + 검색 ( 백업)
    @GetMapping(value="/list222")
    void noteListGET(Model model, @RequestParam(value = "num", required = false, defaultValue = "1") int num,
                     @RequestParam(value = "keyword", required = false, defaultValue ="") String keyword)throws Exception{
//        logger.info("***글 목록을 조회합니다.");
//        List<NoteVO> list = null;
//        list = noteService.listAll();
//        model.addAttribute("list", list);
        logger.info("*** 페이징 처리가 끝난 게시물 목록 조회");

        // ****** 페이징 처리
        PageVO page = new PageVO();
        page.setNum(num);
        page.setCount(noteService.searchCount(keyword));

        // 검색어 받아오기
        page.setKeyword(keyword);

        // 게시물 목록 + 페이징 + 검색
        List<NoteVO> list = null;
        list = noteService.listPageSearch(page.getDisplayPost(), page.getPostNum(), keyword);
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("select", num);

        logger.info("*** 총 게시물:"+list);

    }

    @GetMapping(value = "/list")
    public void noteListSearchGET(Model model, @RequestParam(defaultValue = "1")int page, @RequestParam(value = "keyword", required = false, defaultValue ="")String keyword) throws Exception { // 값이 없을 때 1로 처음에 받아줌

        String board_name = "note"; // 게시판 이름
        int totalListCnt = noteService.searchCount(keyword); // 전체 글 수
        Pagination pagination = new Pagination(totalListCnt, page); // 페이지네이션 객체 생성 후 전체 글 수, page 수 입력

        int startIndex = pagination.getStartIndex(); // sql 검색 처음 시작 인덱스
        int pageSize = pagination.getPageSize(); // 페이지 수
        logger.info("*** 전체 글 수:"+pagination.getTotalListCnt()+" *** 현재 페이지 :"+pagination.getPage()+"***시작페이지 :" + pagination.getStartPage()+"***끝 페이지:"+pagination.getEndPage());

        List<NoteVO> boardList = noteService.noteBoardList(board_name, startIndex, pageSize);
        model.addAttribute("list", boardList); // List형 board를 뷰에 보냄
        model.addAttribute("pagination", pagination);

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

    // 카테고리별 글 보기
    @GetMapping(value="category")
    public void categoryReadGET(@RequestParam("type") String category, Model model) throws Exception{
        logger.info("*** "+category+" 카테고리에 해당하는 글을 조회합니다.");
        List<NoteVO> list = noteService.categoryRead(category);
        logger.info("카테고리 글 전체 :"+list);
        model.addAttribute("list", list);
    }

}
