package com.tinqinacademy.comments.api.modules.operations.postComment;

import com.tinqinacademy.comments.api.modules.base.OperationInput;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PostCommentInput implements OperationInput {

    @Hidden
    private String roomId;
    @Size(min = 1,max = 10)
    @NotNull
    private String firstName;
    @Size(min = 1,max = 10)
    @NotNull
    private String lastName;
    @Size(min = 1,max = 1000)
    @NotNull
    private String content;

}
