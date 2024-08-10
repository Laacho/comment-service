package com.tinqinacademy.comments.api.modules.operations.adminEditComment;

import com.tinqinacademy.comments.api.modules.base.OperationInput;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AdminEditCommentInput implements OperationInput {
    @Hidden
    private String commentId;
    @Size(min = 1,max = 10)
    @NotNull(message = "room number cannot be null")
    private String roomNumber;
    @Size(min = 1,max = 15)
    @NotNull(message = "first name cannot be null")
    private String firstName;
    @Size(min = 1,max = 15)
    @NotNull(message = "last name cannot be null")
    private String lastName;
    @Size(min = 1,max = 255)
    @NotNull(message = "content cannot be empty")
    private String content;
}
