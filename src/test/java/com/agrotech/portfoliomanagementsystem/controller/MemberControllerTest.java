package com.agrotech.portfoliomanagementsystem.controller;

import com.agrotech.portfoliomanagementsystem.controller.MemberController;
import com.agrotech.portfoliomanagementsystem.model.Member;
import com.agrotech.portfoliomanagementsystem.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    @Test
    void getAllMembers() {
        // Arrange
        List<Member> mockMembers = Arrays.asList(new Member(), new Member());
        when(memberService.getAllMembers()).thenReturn(mockMembers);

        // Act
        ResponseEntity<List<Member>> response = memberController.getAllMembers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockMembers, response.getBody());

        // Verify that the memberService.getAllMembers() method was called once
        verify(memberService, times(1)).getAllMembers();
    }

    @Test
    void createMember() {
        // Arrange
        Member mockMember = new Member();
        when(memberService.createMember(any())).thenReturn(mockMember);

        // Act
        ResponseEntity<Member> response = memberController.createMember(mockMember);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockMember, response.getBody());

        // Verify that the memberService.createMember() method was called once with the provided member
        verify(memberService, times(1)).createMember(mockMember);
    }

    @Test
    void associateMemberWithProject() {
        // Arrange
        Member mockMember = new Member();
        when(memberService.associateMemberWithProject(any())).thenReturn(mockMember);

        // Act
        ResponseEntity<Member> response = memberController.associateMemberWithProject(mockMember);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(mockMember, response.getBody());

        // Verify that the memberService.associateMemberWithProject() method was called once with the provided member
        verify(memberService, times(1)).associateMemberWithProject(mockMember);
    }

    @Test
    void disassociateMemberFromProject() {
        // Arrange
        Long projectId = 1L;
        Long personId = 2L;

        // Act
        ResponseEntity<Void> response = memberController.disassociateMemberFromProject(projectId, personId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verify that the memberService.disassociateMemberFromProject() method was called once with the provided project and person IDs
        verify(memberService, times(1)).disassociateMemberFromProject(projectId, personId);
    }
}
