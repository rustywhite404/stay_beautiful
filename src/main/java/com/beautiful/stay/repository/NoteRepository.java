package com.beautiful.stay.repository;

import com.beautiful.stay.domain.NoteVO;

import java.util.List;

public interface NoteRepository {

    // 게시판 글쓰기 C
    void create(NoteVO vo) throws Exception;

    // 게시판 글 목록보기
    List<NoteVO> listAll() throws Exception;

}
