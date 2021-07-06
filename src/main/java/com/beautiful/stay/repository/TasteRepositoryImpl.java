package com.beautiful.stay.repository;

import com.beautiful.stay.domain.TasteVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class TasteRepositoryImpl implements TasteRepository{

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.beautiful.stay.mapper.TasteMapper";

    @Override
    public List<TasteVO> categoryRead(String category) throws Exception {
        return sqlSession.selectList(NAMESPACE+".categoryNote", category);
    }

    @Override
    public int searchCount(String keyword) throws Exception {
        HashMap data = new HashMap();
        data.put("keyword", keyword);
        return sqlSession.selectOne(NAMESPACE+".searchCount", data);
    }

    @Override
    public List<TasteVO> listPageSearch(int displayPost, int postNum, String keyword) throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        data.put("displayPost", displayPost);
        data.put("postNum",postNum);
        data.put("keyword", keyword);
        return sqlSession.selectList(NAMESPACE+".listPageSearch", data);
    }

    @Override
    public List<TasteVO> tasteBoardList(String board_name, int startIndex, int pageSize) throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        data.put("board_name", board_name);
        data.put("startIndex",startIndex);
        data.put("pageSize", pageSize);
        return sqlSession.selectList(NAMESPACE+".listPaging", data);
    }
}
