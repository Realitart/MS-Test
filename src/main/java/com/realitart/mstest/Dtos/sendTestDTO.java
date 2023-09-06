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
public class sendTestDTO {
    private Long id;
    private LocalDateTime start;
    private LocalDateTime expiration;

    private List<sendAnswerDTO> questionsAnsAnswers;

}
