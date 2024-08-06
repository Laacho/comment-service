package com.tinqinacademy.comments.core.services.contracts;

import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentOutput;
import com.tinqinacademy.comments.api.modules.operations.deleteComment.DeleteCommentInput;
import com.tinqinacademy.comments.api.modules.operations.deleteComment.DeleteCommentOutput;

public interface SystemService {
    AdminEditCommentOutput editComment(AdminEditCommentInput input);
    DeleteCommentOutput deleteComment(DeleteCommentInput input);
}
