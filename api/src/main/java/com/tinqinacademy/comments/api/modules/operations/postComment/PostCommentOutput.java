package com.tinqinacademy.comments.api.modules.operations.postComment;


import com.tinqinacademy.comments.api.modules.base.OperationOutput;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PostCommentOutput implements OperationOutput {
    @Size(min = 1,max=10)
    private  String id;
}
