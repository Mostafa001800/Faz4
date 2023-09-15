package com.example.faz3.service.impl;

import com.example.faz3.entity.Comment;
import com.example.faz3.repository.CommentRepository;
import com.example.faz3.service.CommentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;

    @Override
    @Transactional
    public void save(Comment comment) {
        repository.save(comment);
    }
}
