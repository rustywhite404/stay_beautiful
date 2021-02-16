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

    // 글 번호로 내용 조회하기
    @Override
    public NoteVO read(int bno) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".readNote", bno);
    }

    // 글 내용 수정
    @Override
    public void modify(NoteVO vo) throws Exception {
        sqlSession.update(NAMESPACE+".modifyNote", vo);
    }

    // 글 내용 삭제
    @Override
    public void delete(int bno) throws Exception {
        sqlSession.delete(NAMESPACE+".deleteNote",bno);
    }


}
