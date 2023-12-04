package com.agrotech.portfoliomanagementsystem.controller;

import com.agrotech.portfoliomanagementsystem.model.Member;
import com.agrotech.portfoliomanagementsystem.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * Get a list of all members.
     *
     * @return List of members
     */
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    /**
     * Create a new member.
     *
     * @param member The member to be created
     * @return Created member
     */
    @PostMapping("/create")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member createdMember = memberService.createMember(member);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    /**
     * Associate a member with a project.
     *
     * @param member The member to be associated
     * @return Associated member
     */
    @PostMapping("/associate")
    public ResponseEntity<Member> associateMemberWithProject(@RequestBody Member member) {
        Member associatedMember = memberService.associateMemberWithProject(member);
        return new ResponseEntity<>(associatedMember, HttpStatus.CREATED);
    }

    /**
     * Disassociate a member from a project.
     *
     * @param projectId ID of the project
     * @param personId ID of the person
     * @return ResponseEntity with status NO_CONTENT
     */
    @DeleteMapping("/{projectId}/{personId}")
    public ResponseEntity<Void> disassociateMemberFromProject(@PathVariable Long projectId, @PathVariable Long personId) {
        memberService.disassociateMemberFromProject(projectId, personId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}