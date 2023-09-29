package com.realitart.mstest.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAndAnswerDTO {
    private Long questionId;
    private String statement;
    private Integer points;
    List<getAnswerDTO> answers;
}
