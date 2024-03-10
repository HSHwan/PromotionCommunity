package com.promotion_community.PromotionCommunity.services;

import com.promotion_community.PromotionCommunity.models.Board;
import com.promotion_community.PromotionCommunity.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
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

    // 게시글 리스트 로드
    public List<Board> loadBoardList() {
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
}
