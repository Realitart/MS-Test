package com.realitart.mstest.Domain.Repositories;

import com.realitart.mstest.Domain.Question;
import com.realitart.mstest.Domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IQuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByTestId(Test testId);
}
