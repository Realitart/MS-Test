package com.realitart.mstest.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class completeTestDTO {
    private Long id;
    private Integer code;
    private String name;
    private Integer minScoreToAprove;
    private LocalDateTime start;
    private LocalDateTime expiration;

    private List<QuestionAndAnswerDTO> questionsAnsAnswers;

}
