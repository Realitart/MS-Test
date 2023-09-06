package com.realitart.mstest.Domain.Repositories;

import com.realitart.mstest.Domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITestRepository extends JpaRepository<Test, Long> {
    List<Test> findAllByProfessorId(Long professorId);
    Test findByCode(Integer code);
}
