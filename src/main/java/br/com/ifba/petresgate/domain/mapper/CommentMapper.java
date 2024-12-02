package br.com.ifba.petresgate.domain.mapper;

import br.com.ifba.petresgate.domain.Comment;
import br.com.ifba.petresgate.domain.DTOs.CommentDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentMapper {

    public static CommentDTO toCommentDTO(Comment comment) {
        return CommentDTO.builder()
                .fullName(comment.getFullName())
                .content(comment.getContent())
                .build();
    }
}
