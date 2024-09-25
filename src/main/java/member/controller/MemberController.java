    package member.controller;

    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import member.dto.Member;
    import member.dto.MemberDTO;
    import member.service.MemberService;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    @Slf4j
    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/api/members")
    public class MemberController {
        private final MemberService service;

        @GetMapping("/checkemail/{email}") // 이메일 중복 확인
        public ResponseEntity<Boolean> checkEmail(@PathVariable String email) {
            return ResponseEntity.ok().body(service.checkEmailDuplicate(email));
        }

        @GetMapping("/checkname/{username}") // 닉네임 중복 확인
        public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
            return ResponseEntity.ok().body(service.checkNameDuplicate(username));
        }

        @GetMapping("/{uno}") // 회원 조회
        public ResponseEntity<Member> get(@PathVariable int uno) {
            return ResponseEntity.ok(service.getMember(uno));
        }

        @PostMapping("/signup")
        public ResponseEntity<Member> join(@RequestBody MemberDTO memberDTO) throws IllegalAccessException {
            Member member = memberDTO.toMember();
            return ResponseEntity.ok(service.join(member));
        }
//        @PostMapping("")
//        public ResponseEntity<Member> join(MemberJoinDTO member) {
//
//
//            return ResponseEntity.ok(service.join(member));
//
//        }
//        @PutMapping("/{email}")
//        public ResponseEntity<Member> changeProfile(MemberUpdateDTO member){
//            return ResponseEntity.ok(service.update(member));
//        }
    }
