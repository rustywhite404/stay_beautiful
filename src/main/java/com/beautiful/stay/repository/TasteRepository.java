package com.beautiful.stay.repository;

import com.beautiful.stay.domain.NoteVO;
import com.beautiful.stay.domain.TasteVO;

import java.util.List;

public interface TasteRepository {

    // 카테고리 별 글 보기
    List<TasteVO> categoryRead(String category) throws Exception;

    // 게시물 갯수 + 검색
    int searchCount(String keyword) throws Exception;

    // 게시물 목록 + 페이징 + 검색
    List<TasteVO> listPageSearch(int displayPost, int postNum, String keyword) throws Exception;

    // 게시물 목록 + 페이징
    List<TasteVO> tasteBoardList(String board_name, int startIndex, int pageSize) throws Exception;
}
