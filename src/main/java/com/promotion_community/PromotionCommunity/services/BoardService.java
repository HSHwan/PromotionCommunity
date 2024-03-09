package com.promotion_community.PromotionCommunity.services;

import com.promotion_community.PromotionCommunity.models.Board;
import com.promotion_community.PromotionCommunity.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시글 작성 처리
    public void saveBoard(Board board) {
        boardRepository.save(board);
    }

    // 게시글 리스트 처리
    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    // 특정 게시글 불러오기
    public Board loadBoardView(Integer id) {
        return boardRepository.findById(id).get();
    }
}
