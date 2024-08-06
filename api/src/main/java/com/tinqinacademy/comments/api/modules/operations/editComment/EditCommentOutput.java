package com.tinqinacademy.comments.api.modules.operations.editComment;

import com.tinqinacademy.comments.api.modules.base.OperationOutput;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EditCommentOutput implements OperationOutput {

    private String id;
}
