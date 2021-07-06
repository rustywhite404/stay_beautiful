package com.beautiful.stay.controller;

import com.beautiful.stay.domain.NoteVO;
import com.beautiful.stay.domain.Pagination;
import com.beautiful.stay.domain.TasteVO;
import com.beautiful.stay.service.TasteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/taste/*")
public class TasteController {

    @Autowired
    TasteService tasteService;

    private Logger logger = LogManager.getLogger(NoteController.class);

    @GetMapping(value = "/list")
    public void tasteListSearchGET(Model model, @RequestParam(defaultValue = "1")int page, @RequestParam(value = "keyword", required = false, defaultValue ="")String keyword) throws Exception {
        String board_name = "taste"; // 게시판 이름
        int totalListCnt = tasteService.searchCount(keyword); // 전체 글 수
        Pagination pagination = new Pagination(totalListCnt, page); // 페이지네이션 객체 생성 후 전체 글 수, page 수 입력

        int startIndex = pagination.getStartIndex(); // sql 검색 처음 시작 인덱스
        int pageSize = pagination.getPageSize(); // 페이지 수
        logger.info("*** 전체 글 수:"+pagination.getTotalListCnt()+" *** 현재 페이지 :"+pagination.getPage()+"***시작페이지 :" + pagination.getStartPage()+"***끝 페이지:"+pagination.getEndPage());

        List<TasteVO> boardList = tasteService.tasteBoardList(board_name, startIndex, pageSize);
        model.addAttribute("list", boardList); // List형 board를 뷰에 보냄
        model.addAttribute("pagination", pagination);
    }



}
