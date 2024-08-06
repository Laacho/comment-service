package com.tinqinacademy.comments.api.modules.operations.getAllComments;


import com.tinqinacademy.comments.api.modules.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCommentsInput implements OperationInput {

    @NotBlank
    private String roomId;
}
