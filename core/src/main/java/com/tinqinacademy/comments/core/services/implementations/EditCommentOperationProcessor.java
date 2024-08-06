package com.tinqinacademy.comments.core.services.implementations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import com.tinqinacademy.comments.api.modules.exceptions.customException.InvalidCommentIdException;
import com.tinqinacademy.comments.api.modules.exceptions.errorHandler.ErrorHandler;
import com.tinqinacademy.comments.api.modules.exceptions.errorWrapper.ErrorWrapper;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentInput;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentOperation;
import com.tinqinacademy.comments.api.modules.operations.editComment.EditCommentOutput;
import com.tinqinacademy.comments.persistence.entities.Comment;
import com.tinqinacademy.comments.persistence.repositories.CommentsRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EditCommentOperationProcessor implements EditCommentOperation {
    private final ErrorHandler errorHandler;
    private final ObjectMapper objectMapper;
    private final CommentsRepository commentsRepository;
    // private final ConversionService conversionService;

    @Override
    public Either<ErrorWrapper, EditCommentOutput> process(EditCommentInput input) {
        log.info("Start posting comment input: {}", input);
        return Try.of(() -> {
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
                .mapLeft(errorHandler::handleError);
    }

    private EditCommentOutput outputBuilder(Comment save) {
        return EditCommentOutput.builder()
                .id(String.valueOf(save.getId()))
                .build();
    }
}
