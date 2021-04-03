package com.forum.exam9.dto;

import com.forum.exam9.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {
    private String userName;
    private LocalDateTime date;
    private String text;
    private String id;
    public static CommentDto fromAll(Comment comment){
        return CommentDto.builder()
                .id(comment.getId().toString())
                .date(comment.getDate())
                .userName(comment.getUser().getUsername())
                .text(comment.getText())
                .build();
    }
}
