package com.kb.member.controller;

import com.kb.member.dto.Member;
import com.kb.member.dto.MemberDTO;
import com.kb.member.service.MemberService;
import com.sun.net.httpserver.Request;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Api(value = "MemberController", tags = "유저 정보") // swagger
public class MemberController {

    private final MemberService service;

    @GetMapping("/checkemail/{id}") // 이메일 중복 확인
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable String id) {
        return ResponseEntity.ok().body(service.checkEmailDuplicate(id));
    }

    @GetMapping("/checkname/{name}") // 이름 중복 확인
    public ResponseEntity<Boolean> checkNameDuplicate(@PathVariable String name) {
        return ResponseEntity.ok().body(service.checkNameDuplicate(name));
    }

    @GetMapping("/{id}") // 회원 조회
    public ResponseEntity<Member> get(@PathVariable String id) {
        return ResponseEntity.ok(service.getMember(id));
    }


    @PostMapping("/signup") // 회원 가입
    public ResponseEntity<Member> join(@RequestBody MemberDTO memberDTO) throws IllegalAccessException {
        System.out.println(memberDTO.toString());
        Member member = memberDTO.toMember();
        return ResponseEntity.ok(service.join(member));
    }

    @DeleteMapping("/{id}/withdrawal") // 회원 탈퇴
    public ResponseEntity<String> withdrawal(@PathVariable String id) {
        service.deleteMember(id);
        return ResponseEntity.ok("withdrawal successful");
    }
}
