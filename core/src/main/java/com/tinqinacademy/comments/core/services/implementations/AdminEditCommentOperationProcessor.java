package com.tinqinacademy.comments.core.services.implementations;

import com.tinqinacademy.comments.api.modules.exceptions.customException.InvalidCommentIdException;
import com.tinqinacademy.comments.api.modules.exceptions.customException.InvalidUserException;
import com.tinqinacademy.comments.api.modules.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentInput;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentOperation;
import com.tinqinacademy.comments.api.modules.operations.adminEditComment.AdminEditCommentOutput;
import com.tinqinacademy.comments.persistence.entities.Comment;
import com.tinqinacademy.comments.persistence.entities.User;
import com.tinqinacademy.comments.persistence.repositories.CommentsRepository;
import com.tinqinacademy.comments.persistence.repositories.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminEditCommentOperationProcessor implements AdminEditCommentOperation {
    private final ErrorHandler errorHandler;
    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;

    @Override
    public Either<ErrorWrapper, AdminEditCommentOutput> process(AdminEditCommentInput input) {
        log.info("Start editing comment input: {}", input);
        return Try.of(() -> {
                    Comment comment = commentsRepository.findById(UUID.fromString(input.getCommentId())).orElseThrow(
                            () -> new InvalidCommentIdException("Comment Not Found!")
                    );
                    User user = comment.getUser();
                    comment.setContent(input.getContent());
                    user.setFirstName(input.getFirstName());
                    user.setLastName(input.getLastName());
                    comment.setUser(user);
                    comment.setRoomId(UUID.fromString(input.getRoomNumber()));
                    comment.setLastEditedDate(LocalDate.now());
                    Comment savedComment = commentsRepository.save(comment);

                    AdminEditCommentOutput output = outputBuilder(savedComment);
                    log.info("End AdminEditComment output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleError);

    }

    private AdminEditCommentOutput outputBuilder(Comment savedComment) {
        return AdminEditCommentOutput.builder()
                .id(String.valueOf(savedComment.getId())).build();
    }
}
