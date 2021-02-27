package com.beautiful.stay.repository;

import com.beautiful.stay.domain.NoteVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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

    // 카테고리별 글 보기
    @Override
    public List<NoteVO> categoryRead(String category) throws Exception {
        return sqlSession.selectList(NAMESPACE+".categoryNote", category);
    }

    // 총 게시물 갯수 + 검색
    @Override
    public int searchCount(String keyword) throws Exception {
        HashMap data = new HashMap();
        data.put("keyword", keyword);
        return sqlSession.selectOne(NAMESPACE+".searchCount", data);
    }

    @Override
    public List<NoteVO> listPageSearch(int displayPost, int postNum, String keyword) throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        data.put("displayPost", displayPost);
        data.put("postNum",postNum);
        data.put("keyword", keyword);
        return sqlSession.selectList(NAMESPACE+".listPageSearch", data);
    }

    @Override
    public List<NoteVO> noteBoardList(String board_name, int startIndex, int pageSize) throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        data.put("board_name", board_name);
        data.put("startIndex",startIndex);
        data.put("pageSize", pageSize);
        return sqlSession.selectList(NAMESPACE+".listPaging", data);
    }


}
