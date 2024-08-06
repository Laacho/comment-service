package com.tinqinacademy.comments.api.modules.operations.adminEditComment;

import com.tinqinacademy.comments.api.modules.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AdminEditCommentOutput implements OperationOutput {
    private String id;
}
