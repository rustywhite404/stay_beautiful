package com.beautiful.stay.service;

import com.beautiful.stay.domain.NoteVO;

import java.util.List;

public interface NoteService {

    // 게시판 글쓰기 C
    void create(NoteVO vo) throws Exception;

    // 글 목록 전체보기
    List<NoteVO> listAll() throws Exception;


}
