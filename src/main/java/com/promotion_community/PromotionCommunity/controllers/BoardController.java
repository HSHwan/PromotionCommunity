package com.promotion_community.PromotionCommunity.controllers;

import com.promotion_community.PromotionCommunity.models.Board;
import com.promotion_community.PromotionCommunity.services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

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
    public String writeBoard(Board board, Model model, MultipartFile file) throws Exception {
        boardService.writeBoard(board, file);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }

    @GetMapping("/board/list")
    public String showBoardList(Model model,
                                @PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                String searchKeyword) {
        Page<Board> list = null;
        if (searchKeyword == null){
            list = boardService.getBoardList(pageable);
        } else {
            list = boardService.getBoardSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 10, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardlist";
    }

    @GetMapping("/board/view/{id}")
    public String showBoardView(@PathVariable("id") Integer id,
                               Model model) {
        model.addAttribute("board", boardService.getBoardView(id));
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
        model.addAttribute("board", boardService.getBoardView(id));
        return "boardedit";
    }

    @PostMapping("/board/update/{id}")
    public String updateBoardView(@PathVariable("id") Integer id, Board newBoard,
                                  Model model, MultipartFile file) throws Exception {
        Board oldBoard = boardService.getBoardView(id);
        oldBoard.setTitle(newBoard.getTitle());
        oldBoard.setContent(newBoard.getContent());
        boardService.writeBoard(oldBoard, file);

        model.addAttribute("message", "글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        return "message";
    }
}
