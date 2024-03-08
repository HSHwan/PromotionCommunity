package com.promotion_community.PromotionCommunity.controllers;

import com.promotion_community.PromotionCommunity.models.Board;
import com.promotion_community.PromotionCommunity.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/write")
    public String getBoardForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writeprocess")
    public void writeBoard(Board board) {
        boardService.write(board);
    }

    @GetMapping("/board/list")
    public String getBoardList(Model model) {
        model.addAttribute("list", boardService.boardList());
        return "boardlist";
    }
}
