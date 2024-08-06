package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentOperation;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentOutput;
import com.tinqinacademy.comments.api.modules.operations.deleteComment.DeleteCommentInput;
import com.tinqinacademy.comments.api.modules.operations.deleteComment.DeleteCommentOperation;
import com.tinqinacademy.comments.api.modules.operations.deleteComment.DeleteCommentOutput;
import com.tinqinacademy.comments.rest.paths.URLPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SystemController extends BaseController {
    private final AdminEditCommentOperation adminEditCommentOperation;
    private final DeleteCommentOperation deleteCommentOperation;

    public SystemController(AdminEditCommentOperation adminEditCommentOperation, DeleteCommentOperation deleteCommentOperation) {
        this.adminEditCommentOperation = adminEditCommentOperation;
        this.deleteCommentOperation = deleteCommentOperation;
    }

    @PutMapping(URLPaths.SYSTEM_COMMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Comment not found")})
    @Operation(summary = "admin edits comments")
    public ResponseEntity<?> editComment(@PathVariable String commentId,  @RequestBody AdminEditCommentInput input){
        AdminEditCommentInput inputFinal = AdminEditCommentInput.builder()
                .commentId(commentId)
                .content(input.getContent())
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .roomNumber(input.getRoomNumber())
                .build();
        Either<ErrorWrapper, AdminEditCommentOutput> result = adminEditCommentOperation.process(inputFinal);
        return handleResponse(result);
    }

    @DeleteMapping(URLPaths.SYSTEM_COMMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Comment not found")})
    @Operation(summary = "Deletes a comment")
    public ResponseEntity<?> deleteComment(@PathVariable String commendId){

        DeleteCommentInput input = DeleteCommentInput.builder()
                .commentId(commendId)
                .build();
        Either<ErrorWrapper, DeleteCommentOutput> process = deleteCommentOperation.process(input);
        return  handleResponse(process);

    }
}
