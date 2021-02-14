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
}
