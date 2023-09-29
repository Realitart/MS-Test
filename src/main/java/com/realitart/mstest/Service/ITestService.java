package com.realitart.mstest.Service;

import com.realitart.mstest.Domain.Test;
import com.realitart.mstest.Dtos.IFullTest;
import com.realitart.mstest.Dtos.completeTestDTO;
import com.realitart.mstest.Dtos.sendTestDTO;
import com.realitart.mstest.share.response.OperationResponse;

import java.util.List;

public interface ITestService {
    OperationResponse createTest(Test request);

    OperationResponse updateTest(Long TestId, Test request);

    OperationResponse deleteTest(Long TestId);

    Test getTest(Long TestId);

    completeTestDTO checkTestByUser(Long userId, Long TestId);

    completeTestDTO getTestByCode(Integer code);
    completeTestDTO getCompleteTest(Long TestId);

    OperationResponse sendTest(sendTestDTO test, Long userId);

    List<Test> getTests();
    List<Test> getAllTestsByProfessorId(Long professorId);
    List<IFullTest> getAllTestsByStudentId(Long studentId);

}
