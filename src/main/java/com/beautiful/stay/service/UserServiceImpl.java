package com.beautiful.stay.service;

import com.beautiful.stay.domain.UserVO;
import com.beautiful.stay.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LogManager.getLogger(UserServiceImpl.class);

    // 회원가입
    @Override
    public void insertUser(UserVO vo) {

       logger.info("*** 컨트롤러에서 회원가입 동작을 호출하였습니다.");
        if(vo == null){
            logger.info("*** 회원정보가 정상적으로 넘어오지 않았습니다.");
            return;
        }
        userRepository.insertUser(vo);
    }

    // 로그인 처리
    @Override
    public UserVO loginUser(UserVO vo) {
        UserVO returnVO = null;
        try {
            returnVO = userRepository.readUserWithIDPW(vo.getId(), vo.getPassword());
        }catch (Exception e){
            logger.error("*** 로그인 처리 중 에러 발생!");
            returnVO = null;
        }
        return returnVO;
    }

    // 회원 전체 조회
    @Override
    public List<Map<String, Object>> getUser() throws Exception {
        return userRepository.getUser();
    }



}
