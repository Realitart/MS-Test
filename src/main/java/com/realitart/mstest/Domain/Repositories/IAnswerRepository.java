package com.realitart.mstest.Domain.Repositories;

import com.realitart.mstest.Domain.Answer;
import com.realitart.mstest.Domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Question questionId);
}
