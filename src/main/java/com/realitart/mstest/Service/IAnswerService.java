package com.realitart.mstest.Service;

import com.realitart.mstest.Domain.Answer;
import com.realitart.mstest.share.response.OperationResponse;

import java.util.List;

public interface IAnswerService {
    OperationResponse createAnswer(Answer request);

    OperationResponse updateAnswer(Long AnswerId, Answer request);

    OperationResponse deleteAnswer(Long AnswerId);

    Answer getAnswer(Long AnswerId);

    List<Answer> getAnswers();


}
