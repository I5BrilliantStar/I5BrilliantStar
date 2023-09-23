package com.example.project02.service;

import com.example.project02.entity.Board;
import com.example.project02.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    public Board createBoard(Board newBoard) {
        return boardRepository.save(newBoard);
    }

    public Board updateBoard(Long id, Board updatedBoard) {
        Optional<Board> existingBoard = boardRepository.findById(id);
        if (existingBoard.isPresent()) {
            updatedBoard.setId(id);
            return boardRepository.save(updatedBoard);
        } else {
            // 처리할 예외 또는 오류 처리 추가
            throw new RuntimeException("Board not found with id: " + id);
        }
    }

    public boolean deleteBoard(Long id) {
        if (boardRepository.existsById(id)) {
            boardRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
