package com.promotion_community.PromotionCommunity.services;

import com.promotion_community.PromotionCommunity.models.Board;
import com.promotion_community.PromotionCommunity.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시글 작성
    public void writeBoard(Board board) {
        boardRepository.save(board);
    }

    // 게시글 리스트
    public List<Board> getBoardList() {
        return boardRepository.findAll();
    }

    // 특정 게시글 로드
    public Board loadBoardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    // 특정 게시글 삭제
    public void deleteBoardView(Integer id) {
        boardRepository.deleteById(id);
    }

    // 특정 게시글 수정
    public void editBoardView(Integer id) {
        Optional<Board> board = boardRepository.findById(id);

    }
}
