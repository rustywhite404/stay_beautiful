package com.beautiful.stay.repository;


import com.beautiful.stay.domain.UserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.beautiful.stay.mapper.UserMapper";

    // 전체 회원 조회
    @Override
    public List<Map<String, Object>> getUser() throws Exception {
        return sqlSession.selectList(NAMESPACE + ".getUser");
    }

    // ID,PW에 해당하는 정보 조회
    @Override
    public UserVO readUserWithIDPW(String id, String password) throws Exception {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id", id);
        paraMap.put("password", password);
        // key-value : 이때 key 값은 sql 구문의 #{ooo} 이름과 같아야함

        UserVO vo = sqlSession.selectOne(NAMESPACE+".readWithIDPW", paraMap);
        return vo;
    }


    // 회원가입
    @Override
    public void insertUser(UserVO vo) {
        sqlSession.insert(NAMESPACE + ".insertUser", vo);
    }


}
