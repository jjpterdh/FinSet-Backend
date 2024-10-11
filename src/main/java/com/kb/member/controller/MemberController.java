package com.kb.member.controller;

import com.kb.member.dto.Member;
import com.kb.member.dto.MemberDTO;
import com.kb.member.dto.MemberTypeDTO;
import com.kb.member.service.KaKaoLoginService;
import com.kb.member.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Api(value = "MemberController", tags = "유저 정보") // swagger
public class MemberController {

    private final MemberService service;
    private final KaKaoLoginService kaKaoLoginService;
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

    @GetMapping("/{id}/type") // 회원 투자 성향 결과 조회
    public ResponseEntity<MemberTypeDTO> getType(@PathVariable String id) {
        return ResponseEntity.ok(service.getType(id));
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

    @PatchMapping("/{id}/name") // 회원 닉네임 변경
    public ResponseEntity<String> updateName(@PathVariable String id, @RequestBody MemberDTO memberDTO) {
        String name = memberDTO.getName();
        service.updateName(id, name);
        return ResponseEntity.ok("modify name successful");
    }

    @PatchMapping("/{id}/type") // 회원 투자성향 타입 변경
    public ResponseEntity<String> updateType(@PathVariable String id, @RequestBody MemberDTO memberDTO) {
        int type = memberDTO.getType();
        service.updateType(id, type);
        return ResponseEntity.ok("modify type successful");
    }
    @GetMapping("/signup/kakaoInfo/{code}")
    public ResponseEntity<Map<String, Object>> getKakaoInfo(@PathVariable String code) throws IOException {
        String enrollUrl = "http://localhost:5173/auth/kakaojoin";
        String token = kaKaoLoginService.getToken(code, enrollUrl);
        Map<String, Object> map = kaKaoLoginService.getUserInfo(token);
        return ResponseEntity.ok(map);
    }
}
