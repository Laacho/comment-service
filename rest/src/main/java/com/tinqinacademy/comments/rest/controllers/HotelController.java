package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentInput;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentOperation;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentOutput;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.GetAllCommentsInput;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.GetAllCommentsOperation;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.GetAllCommentsOutput;
import com.tinqinacademy.comments.api.modules.operations.postComment.PostCommentInput;
import com.tinqinacademy.comments.api.modules.operations.postComment.PostCommentOperation;
import com.tinqinacademy.comments.core.services.paths.CommentsURLPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class HotelController extends BaseController {

    private final PostCommentOperation postCommentOperation;
    private final GetAllCommentsOperation getAllCommentsOperation;
    private final EditCommentOperation editCommentOperation;

    public HotelController(PostCommentOperation postCommentOperation, GetAllCommentsOperation getAllCommentsOperation, EditCommentOperation editCommentOperation) {
        this.postCommentOperation = postCommentOperation;
        this.getAllCommentsOperation = getAllCommentsOperation;
        this.editCommentOperation = editCommentOperation;
    }


    @GetMapping(CommentsURLPaths.GET_ROOM_COMMENT)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Comment not found")})
    @Operation(summary = "get All Comments ")
    public ResponseEntity<?> getAllComments(@PathVariable String roomID){
        GetAllCommentsInput input = GetAllCommentsInput.builder()
                .roomId(roomID)
                .build();
        Either<ErrorWrapper, GetAllCommentsOutput> result = getAllCommentsOperation.process(input);
       return handleResponse(result);
    }

    @PostMapping(CommentsURLPaths.POST_ROOM_COMMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Comment not found")})
    @Operation(summary = "post comment")
    public ResponseEntity<?> postComments(@PathVariable String roomID,
       @RequestBody PostCommentInput input) {
        PostCommentInput result = PostCommentInput.builder()
                .roomId(roomID)
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .content(input.getContent())
                .build();
       return handleResponse(postCommentOperation.process(result));
    }
    @PatchMapping(value = CommentsURLPaths.PATCH_HOTEL_COMMENT, consumes = "application/json-patch+json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Comment not found")})
    @Operation(summary = "partial update of a comment")
    public ResponseEntity<?> editComment(@PathVariable String commentId,@RequestBody String content){
        EditCommentInput input = EditCommentInput.builder()
                .content(content)
                .id(UUID.fromString(commentId))
                .build();
        Either<ErrorWrapper, EditCommentOutput> process = editCommentOperation.process(input);
        return handleResponse(process);
    }

}
