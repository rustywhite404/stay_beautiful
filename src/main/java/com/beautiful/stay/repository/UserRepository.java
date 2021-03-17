package com.beautiful.stay.repository;

import com.beautiful.stay.domain.UserVO;

import java.util.List;
import java.util.Map;

public interface UserRepository {

    // 전체 회원목록
    List<Map<String,Object>> getUser() throws Exception;

    // 회원 정보 조회 - IP, PW정보에 해당하는 사용자 정보
    public UserVO readUserWithIDPW(String id, String password) throws Exception;

    // ID에 해당하는 사용자 조회
    UserVO findUser(String id) throws Exception;

    // 회원가입
    void insertUser(UserVO vo);

    // 아이디 중복확인
    public int idCheck(String id);


}
