package com.realitart.mstest.Service;

import com.realitart.mstest.Domain.Question;
import com.realitart.mstest.share.response.OperationResponse;

import java.util.List;

public interface IQuestionService {
    OperationResponse createQuestion(Question request);

    OperationResponse updateQuestion(Long QuestionId, Question request);

    OperationResponse deleteQuestion(Long QuestionId);

    Question getQuestion(Long QuestionId);

    List<Question> getQuestions();
}
