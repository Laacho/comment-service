package com.tinqinacademy.comments.core.services.implementations;

import com.tinqinacademy.comments.api.modules.exceptions.customException.InvalidCommentIdException;
import com.tinqinacademy.comments.api.modules.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.deleteComment.DeleteCommentInput;
import com.tinqinacademy.comments.api.modules.operations.deleteComment.DeleteCommentOperation;
import com.tinqinacademy.comments.api.modules.operations.deleteComment.DeleteCommentOutput;
import com.tinqinacademy.comments.persistence.repositories.CommentsRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeleteCommentOperationProcessor implements DeleteCommentOperation {
    private final ErrorHandler errorHandler;
    private final CommentsRepository commentsRepository;

    @Override
    public Either<ErrorWrapper, DeleteCommentOutput> process(DeleteCommentInput input) {
        log.info("Start posting comment input: {}", input);
        return Try.of(() -> {
                    if (!commentsRepository.existsById(UUID.fromString(input.getCommentId())))
                        throw new InvalidCommentIdException("Invalid comment id");
                    commentsRepository.deleteById(UUID.fromString(input.getCommentId()));
                    DeleteCommentOutput output = outputBuilder();
                    log.info("End deleting comment output: {}", output);
                    return output;

                })
                .toEither()
                .mapLeft(errorHandler::handleError);


    }

    private DeleteCommentOutput outputBuilder() {
        return DeleteCommentOutput.builder()
                .message("Deleted successfully")
                .build();
    }
}
