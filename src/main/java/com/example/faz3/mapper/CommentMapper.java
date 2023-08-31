package com.example.faz3.mapper;

import com.example.faz3.dto.CommentDto;
import com.example.faz3.entity.Comment;

public class CommentMapper {
    public Comment convert(CommentDto commentDto){
        Comment comment=new Comment();
        comment.setTitle(commentDto.getTitle());
        comment.setScore(commentDto.getScore());
        return comment;
    }
}
