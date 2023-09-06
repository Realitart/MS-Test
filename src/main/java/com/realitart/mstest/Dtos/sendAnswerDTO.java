package com.realitart.mstest.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class sendAnswerDTO {
    private  Long questionId;
    private Long answerId;
}
