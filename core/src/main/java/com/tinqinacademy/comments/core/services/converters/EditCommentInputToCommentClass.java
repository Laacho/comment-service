package com.tinqinacademy.comments.core.services.converters;

import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentInput;
import com.tinqinacademy.comments.persistence.entities.Comment;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EditCommentInputToCommentClass implements Converter<EditCommentInput, Comment> {
    @Override
    public Comment convert(EditCommentInput source) {
        return Comment.builder()
                .id(source.getId())
                .content(source.getContent())
                .build();
    }
}
