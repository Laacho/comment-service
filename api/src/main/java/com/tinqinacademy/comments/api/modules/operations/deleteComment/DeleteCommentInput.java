package com.tinqinacademy.comments.api.modules.operations.deleteComment;

import com.tinqinacademy.comments.api.modules.base.OperationInput;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCommentInput implements OperationInput {
    @Hidden
    private String commentId;

}
