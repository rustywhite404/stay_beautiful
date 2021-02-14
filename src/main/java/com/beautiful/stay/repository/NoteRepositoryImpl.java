package com.beautiful.stay.repository;

import com.beautiful.stay.domain.NoteVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteRepositoryImpl implements NoteRepository{

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.beautiful.stay.mapper.NoteMapper";

    // 게시판 글쓰기 C
    @Override
    public void create(NoteVO vo) throws Exception {
        sqlSession.insert(NAMESPACE+".createNote", vo);
    }

    // 게시판 글 전체보기
    @Override
    public List<NoteVO> listAll() throws Exception {
        return sqlSession.selectList(NAMESPACE+".listNote");
    }


}
