package com.beautiful.stay.service;

import com.beautiful.stay.domain.TasteVO;
import com.beautiful.stay.repository.TasteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasteServiceImpl implements TasteService{

    @Autowired
    private TasteRepository tasteRepository;

    private Logger logger = LogManager.getLogger(NoteServiceImpl.class);

    @Override
    public List<TasteVO> categoryRead(String category) throws Exception {
        return tasteRepository.categoryRead(category);
    }

    @Override
    public int searchCount(String keyword) throws Exception {
        return tasteRepository.searchCount(keyword);
    }

    @Override
    public List<TasteVO> listPageSearch(int displayPost, int postNum, String keyword) throws Exception {
        return tasteRepository.listPageSearch(displayPost, postNum, keyword);
    }

    @Override
    public List<TasteVO> tasteBoardList(String board_name, int startIndex, int pageSize) throws Exception {
        return tasteRepository.tasteBoardList(board_name, startIndex, pageSize);
    }
}
