package com.sigsauer.epsiloan.lending.repository;

import com.sigsauer.epsiloan.lending.entity.DefaultUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultUserRepository extends JpaRepository<DefaultUser, Long> {
}
