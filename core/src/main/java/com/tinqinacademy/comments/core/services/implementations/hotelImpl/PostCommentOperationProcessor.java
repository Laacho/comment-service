package com.tinqinacademy.comments.core.services.implementations.hotelImpl;

import com.tinqinacademy.comments.api.modules.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.postComment.PostCommentInput;
import com.tinqinacademy.comments.api.modules.operations.postComment.PostCommentOperation;
import com.tinqinacademy.comments.api.modules.operations.postComment.PostCommentOutput;
import com.tinqinacademy.comments.core.services.implementations.BaseOperationProcessor;
import com.tinqinacademy.comments.persistence.entities.Comment;
import com.tinqinacademy.comments.persistence.entities.User;
import com.tinqinacademy.comments.persistence.repositories.CommentsRepository;
import com.tinqinacademy.comments.persistence.repositories.UserRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
public class PostCommentOperationProcessor extends BaseOperationProcessor implements PostCommentOperation {
    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;

    public PostCommentOperationProcessor(Validator validator, ErrorHandler errorHandler, CommentsRepository commentsRepository, UserRepository userRepository) {
        super(validator, errorHandler);
        this.commentsRepository = commentsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Either<ErrorWrapper, PostCommentOutput> process(PostCommentInput input) {
        log.info("Start posting comment input: {}",input);
        return validateInput(input).flatMap(validatedInput ->Try.of(()-> {

                    User user = getUser(input);
                    User savedUser = userRepository.save(user);
                    Comment comment = buildComment(input,savedUser);
                    Comment save = commentsRepository.save(comment);
                    PostCommentOutput output = buildOutput(save);
                    log.info("End posting comment output: {}",output);
            return output;
                }
        )
                .toEither()
                .mapLeft(errorHandler::handleError));
    }

    private  User getUser(PostCommentInput input) {
        return User.builder()
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .birthDate(LocalDate.now())
                .build();
    }

    private  PostCommentOutput buildOutput(Comment save) {
        return PostCommentOutput.builder()
                        .id(String.valueOf(save.getId()))
                        .build();
    }

    private  Comment buildComment(PostCommentInput input, User savedUser) {
        return Comment.builder()
                        .content(input.getContent())
                        .publishedDate(LocalDate.now())
                        .lastEditedDate(LocalDate.now())
                        .roomId(UUID.fromString(input.getRoomId()))
                        .lastEditeBy(input.getFirstName())
                        .user(savedUser)
                        .build();
    }
}
