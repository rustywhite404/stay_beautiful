package com.beautiful.stay.service;

import com.beautiful.stay.domain.NoteVO;
import com.beautiful.stay.repository.NoteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteRepository noteRepository;

    private Logger logger = LogManager.getLogger(NoteServiceImpl.class);

    // 게시판 글쓰기 C
    @Override
    public void create(NoteVO vo) throws Exception {
        noteRepository.create(vo);
    }

    // 글 전체보기
    @Override
    public List<NoteVO> listAll() throws Exception {
        return noteRepository.listAll();
    }

    // 글 번호로 내용 조회
    @Override
    public NoteVO read(int bno) throws Exception {
        return noteRepository.read(bno);
    }

    // 글 내용 수정
    @Override
    public void modify(NoteVO vo) throws Exception {
        noteRepository.modify(vo);
    }

    // 글 내용 삭제
    @Override
    public void delete(int bno) throws Exception {
        noteRepository.delete(bno);
    }

    // 카테고리별 글 보기
    @Override
    public List<NoteVO> categoryRead(String category) throws Exception {
        return noteRepository.categoryRead(category);
    }

    // 게시물 총 갯수 + 검색
    @Override
    public int searchCount(String keyword) throws Exception {
        return noteRepository.searchCount(keyword);
    }

    // 게시물 목록 + 페이징 + 검색
    @Override
    public List<NoteVO> listPageSearch(int displayPost, int postNum, String keyword) throws Exception {
        return noteRepository.listPageSearch(displayPost, postNum, keyword);
    }

    @Override
    public List<NoteVO> noteBoardList(String board_name, int startIndex, int pageSize) throws Exception {
        return noteRepository.noteBoardList(board_name, startIndex, pageSize);
    }


}
