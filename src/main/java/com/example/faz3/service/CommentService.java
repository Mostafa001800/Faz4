package com.example.faz3.service;

import com.example.faz3.entity.Comment;
import com.example.faz3.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository repository;
//@Autowired
//    public CommentService(CommentRepository repository) {
//        this.repository = repository;
//    }
@Transactional
    public void save(Comment comment){
        repository.save(comment);
    }
}
