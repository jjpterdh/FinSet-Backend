package com.kb.member.controller;

import com.kb.member.dto.Member;
import com.kb.member.dto.MemberDTO;
import com.kb.member.service.MemberService;
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

    @GetMapping("/checkid/{id}") // 이메일 중복 확인
    public ResponseEntity<Boolean> checkDuplicate(@PathVariable String id) {
        return ResponseEntity.ok().body(service.checkDuplicate(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> get(@PathVariable String id) {
        return ResponseEntity.ok(service.getMember(id));
    }


    @PostMapping("/signup")
    public ResponseEntity<Member> join(@RequestBody MemberDTO memberDTO) throws IllegalAccessException {
        System.out.println(memberDTO.toString());
        Member member = memberDTO.toMember();
        return ResponseEntity.ok(service.join(member));
    }
}
