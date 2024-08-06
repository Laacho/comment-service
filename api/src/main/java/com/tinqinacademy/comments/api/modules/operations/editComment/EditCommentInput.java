package com.tinqinacademy.comments.api.modules.operations.editComment;

import com.tinqinacademy.comments.api.modules.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EditCommentInput implements OperationInput {
    @Size(min = 1)
    @NotNull
    private String content;

    @NotBlank
    private UUID id;
}


