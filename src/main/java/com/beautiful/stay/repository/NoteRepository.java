package com.beautiful.stay.repository;

import com.beautiful.stay.domain.NoteVO;

import java.util.List;

public interface NoteRepository {

    // 게시판 글쓰기 C
    void create(NoteVO vo) throws Exception;

    // 게시판 글 목록보기
    List<NoteVO> listAll() throws Exception;

    // 글 번호로 내용 조회
    NoteVO read(int bno) throws Exception;

    // 글 수정하기
    void modify(NoteVO vo) throws Exception;

    // 글 삭제하기
    void delete(int bno) throws Exception;

    // 카테고리 별 글 보기
    List<NoteVO> categoryRead(String category) throws Exception;

    // 게시물 갯수 + 검색
    int searchCount(String keyword) throws Exception;

    // 게시물 목록 + 페이징 + 검색
    List<NoteVO> listPageSearch(int displayPost, int postNum, String keyword) throws Exception;

    // 게시물 목록 + 페이징
    List<NoteVO> noteBoardList(String board_name, int startIndex, int pageSize) throws Exception;

}
