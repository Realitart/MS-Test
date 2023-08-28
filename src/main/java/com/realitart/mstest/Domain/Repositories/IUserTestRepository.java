package com.realitart.mstest.Domain.Repositories;

import com.realitart.mstest.Domain.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserTestRepository extends JpaRepository<UserTest, Long> {

    Boolean existsByAnswerIdAndUserId(Long answerId, Long userId);
}
