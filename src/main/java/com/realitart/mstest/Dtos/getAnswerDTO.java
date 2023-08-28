package com.realitart.mstest.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class getAnswerDTO {
    private Long id;
    private String answer;
    private Boolean isCorrect;
    private Boolean isChosen;
}
