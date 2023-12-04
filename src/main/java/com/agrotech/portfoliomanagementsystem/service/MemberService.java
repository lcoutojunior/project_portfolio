package com.agrotech.portfoliomanagementsystem.service;

import com.agrotech.portfoliomanagementsystem.model.Member;
import com.agrotech.portfoliomanagementsystem.model.Person;
import com.agrotech.portfoliomanagementsystem.repository.MemberRepository;
import com.agrotech.portfoliomanagementsystem.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class for handling business logic related to the {@link Member} entity.
 */
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * Get a list of all members.
     *
     * @return List of members
     */
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    /**
     * Create a new member.
     *
     * @param member The member to be created
     * @return Created member
     */
    public Member createMember(Member member) {
        // Implement your logic for creating a member
        // You might want to validate the input, set default values, etc.
        // For simplicity, let's assume you just save the member to the repository.
        return memberRepository.save(member);
    }

    /**
     * Associate a member with a project.
     *
     * @param member The member to be associated
     * @return Associated member
     */
    public Member associateMemberWithProject(Member member) {
        // Implement your logic for associating a member with a project
        // You might want to validate the input, check if the project exists, etc.
        // For simplicity, let's assume you just save the member to the repository.
        return memberRepository.save(member);
    }

    /**
     * Disassociate a member from a project.
     *
     * @param projectId ID of the project
     * @param personId ID of the person
     */
    public void disassociateMemberFromProject(Long projectId, Long personId) {
        // Implement your logic for disassociating a member from a project
        // For simplicity, let's assume you just delete the association from the repository.
        memberRepository.deleteByProjectIdAndPersonId(projectId, personId);
    }
}