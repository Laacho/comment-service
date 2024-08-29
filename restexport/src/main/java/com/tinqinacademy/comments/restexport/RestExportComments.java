package com.tinqinacademy.comments.restexport;

import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentOutput;
import com.tinqinacademy.comments.api.modules.operations.deleteComment.DeleteCommentOutput;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentOutput;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.GetAllCommentsOutput;
import com.tinqinacademy.comments.api.modules.operations.postComment.PostCommentInput;
import com.tinqinacademy.comments.api.modules.operations.postComment.PostCommentOutput;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("comments-service")
public interface RestExportComments {
    //HOTEL
   // @GetMapping(URLPaths.ROOM_COMMENT)
    @RequestLine("GET /hotel/{roomID}/comment?roomID={roomID}")
    GetAllCommentsOutput getAllComments(@Param("roomID") String roomID);

   // @PostMapping(URLPaths.ROOM_COMMENT)
    @RequestLine("POST /hotel/{roomID}/comment?roomID={roomID}&input={input}")
    PostCommentOutput postComments(@Param("roomID") String roomID,
                                   @Param("input") PostCommentInput input);

   //@PatchMapping(value = URLPaths.HOTEL_COMMENT, consumes = "application/json-patch+json")
    @RequestLine("PATCH /hotel/comment/{commentId}?commentId={commentId}&content={content}")
    EditCommentOutput editComment(@Param("commentId") String commentId,
                                  @Param("content") String content);
    //SYSTEM
    //@PutMapping(URLPaths.SYSTEM_COMMENT)
    @RequestLine("PUT /system/comment/{commentId}?commentId={commentId}&input={input}")
    AdminEditCommentOutput adminEditComment(@Param("commentId") String commentId,
                                       @Param("input") AdminEditCommentInput input);

    //@DeleteMapping(URLPaths.SYSTEM_COMMENT)
    @RequestLine("DELETE /system/comment/{commentId}?commendId={commendId}")
    DeleteCommentOutput deleteComment(@Param("commendId") String commendId);
}
