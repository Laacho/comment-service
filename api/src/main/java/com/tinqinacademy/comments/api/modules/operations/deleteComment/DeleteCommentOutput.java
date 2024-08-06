package com.tinqinacademy.comments.api.modules.operations.deleteComment;

import com.tinqinacademy.comments.api.modules.base.OperationOutput;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCommentOutput implements OperationOutput {
    private String message;
}
