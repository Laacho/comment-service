package com.tinqinacademy.comments.core.services.implementations.hotelImpl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.tinqinacademy.comments.api.modules.exceptions.customException.InvalidCommentIdException;
import com.tinqinacademy.comments.api.modules.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentInput;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentOperation;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentOutput;
import com.tinqinacademy.comments.core.services.implementations.BaseOperationProcessor;
import com.tinqinacademy.comments.persistence.entities.Comment;
import com.tinqinacademy.comments.persistence.repositories.CommentsRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class EditCommentOperationProcessor extends BaseOperationProcessor implements EditCommentOperation { ;
    private final ObjectMapper objectMapper;
    private final CommentsRepository commentsRepository;

    public EditCommentOperationProcessor(Validator validator, ErrorHandler errorHandler, ObjectMapper objectMapper, CommentsRepository commentsRepository) {
        super(validator, errorHandler);
        this.objectMapper = objectMapper;
        this.commentsRepository = commentsRepository;
    }

    @Override
    public Either<ErrorWrapper, EditCommentOutput> process(EditCommentInput input) {
        log.info("Start posting comment input: {}", input);
        return validateInput(input).flatMap(validatedInput ->Try.of(() -> {
                    Comment commentById = commentsRepository.findById(input.getId()).orElseThrow(
                            () -> new InvalidCommentIdException("comment not found"));
                    JsonNode roomNode = objectMapper.valueToTree(commentById);
                    JsonNode inputNode = objectMapper.valueToTree(input);
                    JsonMergePatch patch = JsonMergePatch.fromJson(inputNode);
                    JsonNode patchedNode = patch.apply(roomNode);
                    Comment comments = objectMapper.treeToValue(patchedNode, Comment.class);
                    comments.setLastEditedDate(LocalDate.now());
                    comments.setLastEditeBy(commentById.getUser().getFirstName());
                    log.info("Patched room {}", comments);
                    commentsRepository.save(comments);
                    EditCommentOutput output = outputBuilder(commentById);
                    log.info("End editOwnComments output: {}", output);
                    return output;
                })
                .toEither()
                .mapLeft(errorHandler::handleError));
    }

    private EditCommentOutput outputBuilder(Comment save) {
        return EditCommentOutput.builder()
                .id(String.valueOf(save.getId()))
                .build();
    }
}
