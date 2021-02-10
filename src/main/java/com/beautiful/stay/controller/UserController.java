package com.beautiful.stay.controller;

import com.beautiful.stay.domain.UserVO;
import com.beautiful.stay.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LogManager.getLogger(UserController.class);

    // 회원가입 - 뷰
    @GetMapping("/join")
    public void insertUserGet() throws Exception{
    }

    // 회원가입
    @PostMapping("/join")
    public String insertUserPost(UserVO vo, HttpSession session) throws Exception{
        userService.insertUser(vo);
        return "redirect:/user/login";
    }

    // 로그인
    @GetMapping("/login")
    public void userLoginGet() throws Exception{
        logger.info("*** 로그인 창에 접근했습니다.");
    }

    @PostMapping("/login")
    public String userLoginPost(UserVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
        logger.info("*** 회원 로그인을 시도합니다.");
        logger.info("*** 전달받은 정보 확인:"+vo);
        UserVO loginCheck = userService.loginUser(vo);
        logger.info("*** 결과 조회:"+loginCheck);
        // 해당 결과에 따라 페이지 이동 제어
        if(loginCheck == null){ // 해당 정보가 없을 경우 -> login 페이지로
            return "redirect:/user/login";
        }
        // 정보가 있을 경우
        // 세션값 생성
        session.setAttribute("id", loginCheck.getId());
        session.setAttribute("name", loginCheck.getName());
        logger.info("***"+loginCheck.getName()+" 님이 로그인 성공했습니다.");

        // 페이지 리다이렉트 이동 시 RedirectAttributes 객체 사용 정보 전달
        rttr.addFlashAttribute("userInfo", loginCheck);

        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String userLogoutGet(HttpSession session) throws Exception{
        logger.info("*** 로그아웃합니다.");
        session.invalidate();
        return "redirect:/";
    }


    // 전체회원조회
    @GetMapping("/test")
    public List<Map<String, Object>> getUser() throws Exception{
        return userService.getUser();
    }



}
