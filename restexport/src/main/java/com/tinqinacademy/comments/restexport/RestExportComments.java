package com.tinqinacademy.comments.restexport;

import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentOutput;
import com.tinqinacademy.comments.api.modules.operations.deleteComment.DeleteCommentInput;
import com.tinqinacademy.comments.api.modules.operations.deleteComment.DeleteCommentOutput;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentInput;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentOutput;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.GetAllCommentsOutput;
import com.tinqinacademy.comments.api.modules.operations.postComment.PostCommentInput;
import com.tinqinacademy.comments.core.services.paths.URLPaths;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient("comments-service")
public interface RestExportComments {
    //HOTEL
    @GetMapping(URLPaths.ROOM_COMMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Comment not found")})
    GetAllCommentsOutput getAllComments(@PathVariable String roomID);

    @PostMapping(URLPaths.ROOM_COMMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Comment not found")})
    ResponseEntity<?> postComments(@PathVariable String roomID,
                                   @RequestBody PostCommentInput input);

    @PatchMapping(value = URLPaths.HOTEL_COMMENT, consumes = "application/json-patch+json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Comment not found")})
    EditCommentOutput editComment(@PathVariable String commentId, @RequestBody String content);

    //SYSTEM
    @PutMapping(URLPaths.SYSTEM_COMMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Comment not found")})
    AdminEditCommentOutput editComment(@PathVariable String commentId,  @RequestBody AdminEditCommentInput input);

    @DeleteMapping(URLPaths.SYSTEM_COMMENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Comment not found")})
    DeleteCommentOutput deleteComment(@PathVariable String commendId);
}
