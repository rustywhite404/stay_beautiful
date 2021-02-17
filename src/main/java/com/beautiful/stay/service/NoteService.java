package com.beautiful.stay.service;

import com.beautiful.stay.domain.NoteVO;

import java.util.List;

public interface NoteService {

    // 게시판 글쓰기 C
    void create(NoteVO vo) throws Exception;

    // 글 목록 전체보기
    List<NoteVO> listAll() throws Exception;

    // 글 번호로 해당 글 조회
    NoteVO read(int bno) throws Exception;

    // 글 수정하기
    void modify(NoteVO vo) throws Exception;

    // 글 삭제하기
    void delete(int bno) throws Exception;

    // 카테고리별 글 보기
    List<NoteVO> categoryRead(String category) throws Exception;

}
