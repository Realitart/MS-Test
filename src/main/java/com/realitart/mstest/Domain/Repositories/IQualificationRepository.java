package com.realitart.mstest.Domain.Repositories;

import com.realitart.mstest.Domain.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IQualificationRepository extends JpaRepository<Qualification, Long> {


}
