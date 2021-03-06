package com.beautiful.stay.service;

import com.beautiful.stay.domain.UserVO;

import java.util.List;
import java.util.Map;

public interface UserService {

    //회원가입
    void insertUser(UserVO vo);

    //로그인 체크
    public UserVO loginUser(UserVO vo);

    // ID로 유저 조회
    UserVO findUser(String id) throws Exception;

    //유저 전체 조회
    List<Map<String,Object>> getUser() throws Exception;

    // 아이디 중복확인
    public int idCheck(String id);
}
