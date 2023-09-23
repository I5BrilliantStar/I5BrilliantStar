package com.example.project02.controller;

import com.example.project02.entity.Board;
import com.example.project02.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public ResponseEntity<List<Board>> getAllBoards() {
        List<Board> boards = boardService.getAllBoards();
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        Optional<Board> board = boardService.getBoardById(id);
        if (board.isPresent()) {
            return new ResponseEntity<>(board.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        Board createdBoard = boardService.createBoard(board);
        return new ResponseEntity<>(createdBoard, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board updatedBoard) {
        Board board = boardService.updateBoard(id, updatedBoard);
        if (board != null) {
            return new ResponseEntity<>(board, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boolean deleted = boardService.deleteBoard(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Add more endpoints as needed
}
