package com.tinqinacademy.comments.core.services.contracts;

import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentInput;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentOutput;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.GetAllCommentsInput;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.GetAllCommentsOutput;
import com.tinqinacademy.comments.api.modules.operations.postComment.PostCommentInput;
import com.tinqinacademy.comments.api.modules.operations.postComment.PostCommentOutput;

import java.util.List;

public interface HotelService {
    List<GetAllCommentsOutput> getAllCommentsList(GetAllCommentsInput input);
    PostCommentOutput postComment(PostCommentInput input);
    EditCommentOutput editComment(EditCommentInput input);
}
