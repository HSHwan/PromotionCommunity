package com.promotion_community.PromotionCommunity.services;

import com.promotion_community.PromotionCommunity.models.Board;
import com.promotion_community.PromotionCommunity.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class BoardService {

    private BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 게시글 작성
    public void writeBoard(Board board, MultipartFile file) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File newFile = new File(filePath, fileName);
        file.transferTo(newFile);

        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);

        boardRepository.save(board);
    }

    // 게시글 리스트 얻기
    public Page<Board> getBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    // 게시글 검색 결과 얻기
    public Page<Board> getBoardSearchList(String searchKeyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    // 특정 게시글 얻기
    public Board getBoardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    // 특정 게시글 삭제
    public void deleteBoardView(Integer id) {
        boardRepository.deleteById(id);
    }
}
