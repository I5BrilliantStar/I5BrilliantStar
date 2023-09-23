package com.example.project02.service;

import com.example.project02.entity.Comment;
import com.example.project02.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, Comment updatedComment) {
        Optional<Comment> existingComment = commentRepository.findById(id);
        if (existingComment.isPresent()) {
            updatedComment.setId(id);
            return commentRepository.save(updatedComment);
        } else {
            // 처리할 예외 또는 오류 처리 추가
            throw new RuntimeException("Comment not found with id: " + id);
        }
    }


    public boolean deleteComment(Long id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
