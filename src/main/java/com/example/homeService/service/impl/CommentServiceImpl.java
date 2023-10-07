package com.example.homeService.service.impl;

import com.example.homeService.entity.Comment;
import com.example.homeService.repository.CommentRepository;
import com.example.homeService.service.CommentService;
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
