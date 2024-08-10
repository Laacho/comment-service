package com.tinqinacademy.comments.core.services.implementations.hotelImpl;

import com.tinqinacademy.comments.api.modules.exceptions.customException.InvalidUserException;
import com.tinqinacademy.comments.api.modules.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.DataOutput;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.GetAllCommentsInput;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.GetAllCommentsOperation;
import com.tinqinacademy.comments.api.modules.operations.getAllComments.GetAllCommentsOutput;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class GetAllCommentsOperationProcessor extends BaseOperationProcessor implements GetAllCommentsOperation {
    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;

    public GetAllCommentsOperationProcessor(Validator validator, ErrorHandler errorHandler, CommentsRepository commentsRepository, UserRepository userRepository) {
        super(validator, errorHandler);
        this.commentsRepository = commentsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Either<ErrorWrapper, GetAllCommentsOutput> process(GetAllCommentsInput input) {
        log.info("Start posting comment input: {}", input);
        return validateInput(input).flatMap(validatedInput ->Try.of(() -> {
                    List<Comment> allByRoomId = commentsRepository.findAllByRoomId(UUID.fromString(input.getRoomId()));
                    List<DataOutput> result = getDataOutputs(allByRoomId);
                    GetAllCommentsOutput output = buildOutput(result);
                    log.info("End getAllComments  output: {}", output);
                    return output;

                })
                .toEither()
                .mapLeft(errorHandler::handleError));
    }

    private List<DataOutput> getDataOutputs(List<Comment> allByRoomId) {
        List<DataOutput> result = new ArrayList<>();
        for (Comment comment : allByRoomId) {
            User user = userRepository.findById(comment.getUser().getId()).orElseThrow(
                    () -> new InvalidUserException("User not found"));
            DataOutput output = buildDataOutput(comment, user);

            result.add(output);
        }
        return result;
    }

    private DataOutput buildDataOutput(Comment comment, User user) {
        return DataOutput.builder()
                .id(String.valueOf(comment.getId()))
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .content(comment.getContent())
                .publishedDate(comment.getPublishedDate())
                .lastEditedDate(comment.getLastEditedDate())
                .lastEditedBy(comment.getLastEditeBy())
                .build();
    }

    private GetAllCommentsOutput buildOutput(List<DataOutput> result) {
        return GetAllCommentsOutput.builder()
                .comments(result)
                .build();

    }
}
