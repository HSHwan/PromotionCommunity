package com.promotion_community.PromotionCommunity.controllers;

import com.promotion_community.PromotionCommunity.models.Board;
import com.promotion_community.PromotionCommunity.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/write")
    public String showBoardForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writeprocess")
    public String writeBoard(Board board, Model model) {
        boardService.writeBoard(board);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }

    @GetMapping("/board/list")
    public String showBoardList(Model model) {
        model.addAttribute("list", boardService.getBoardList());
        return "boardlist";
    }

    @GetMapping("/board/view/{id}")
    public String showBoardView(@PathVariable("id") Integer id,
                               Model model) {
        model.addAttribute("board", boardService.loadBoardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete/{id}")
    public String deleteBoardView(@PathVariable("id") Integer id,
                                  Model model) {
        boardService.deleteBoardView(id);
        model.addAttribute("message", "글 삭제가 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }

    @GetMapping("/board/edit/{id}")
    public String editBoardView(@PathVariable("id") Integer id,
                                Model model){
        model.addAttribute("board", boardService.loadBoardView(id));
        return "boardedit";
    }

    @PostMapping("/board/update/{id}")
    public String updateBoardView(@PathVariable("id") Integer id, Board newBoard,
                                  Model model){
        Board oldBoard = boardService.loadBoardView(id);
        oldBoard.setTitle(newBoard.getTitle());
        oldBoard.setContent(newBoard.getContent());
        boardService.writeBoard(oldBoard);

        model.addAttribute("message", "글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }
}
