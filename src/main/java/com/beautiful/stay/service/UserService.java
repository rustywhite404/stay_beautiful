package com.beautiful.stay.service;

import com.beautiful.stay.domain.UserVO;

import java.util.List;
import java.util.Map;

public interface UserService {

    //회원가입
    void insertUser(UserVO vo);

    //로그인 체크
    public UserVO loginUser(UserVO vo);


    //유저 전체 조회
    List<Map<String,Object>> getUser() throws Exception;

}
