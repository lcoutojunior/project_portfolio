package com.agrotech.portfoliomanagementsystem.repository;

import com.agrotech.portfoliomanagementsystem.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import jakarta.transaction.Transactional;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByProjectIdAndPersonId(Long projectId, Long personId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Member m WHERE m.project.id = :projectId AND m.person.id = :personId")
    void deleteByProjectIdAndPersonId(Long projectId, Long personId);
}
