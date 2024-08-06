package com.tinqinacademy.comments.api.modules.operations.getAllComments;

import com.tinqinacademy.comments.api.modules.base.OperationOutput;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GetAllCommentsOutput implements OperationOutput {
   private List<DataOutput> comments;
}
