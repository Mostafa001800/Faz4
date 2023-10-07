package com.example.homeService.mapper;

import com.example.homeService.dto.CommentDto;
import com.example.homeService.entity.Comment;

public class CommentMapper {
    public Comment convert(CommentDto commentDto){
        Comment comment=new Comment();
        comment.setTitle(commentDto.getTitle());
        comment.setScore(commentDto.getScore());
        return comment;
    }
}
