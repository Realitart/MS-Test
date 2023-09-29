package com.realitart.mstest.Domain.Repositories;

import com.realitart.mstest.Domain.Test;
import com.realitart.mstest.Dtos.IFullTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITestRepository extends JpaRepository<Test, Long> {
    List<Test> findAllByProfessorId(Long professorId);
    Test findByCode(Integer code);

    //Query("SELECT t FROM Test t WHERE t.professorId = :professorId")
    @Query(nativeQuery = true, value ="SELECT MIN(ustest.created_at) AS created, SUM(ustest.score) AS score, GROUP_CONCAT(t.name ORDER BY ustest.created_at ASC SEPARATOR ', ') AS name FROM test.user_test ustest INNER JOIN test.test t ON ustest.test_id = t.id WHERE user_id = ?1 GROUP BY user_id")
    List<IFullTest> findAllByStudentId(Long studentId);
}
