    package member.controller;

    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import member.dto.ChangePasswordDTO;
    import member.dto.MemberDTO;
    import member.dto.MemberJoinDTO;
    import member.dto.MemberUpdateDTO;
    import member.service.MemberService;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @Slf4j
    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/api/member")
    public class MemberController {
        final MemberService service;

        @GetMapping("/checkemail/{email}")
        public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
            return ResponseEntity.ok().body(service.checkDuplicate(username));
        }
        @PostMapping("")
        public ResponseEntity<MemberDTO> join(MemberJoinDTO member) {


            return ResponseEntity.ok(service.join(member));

        }
        @PutMapping("/{email}")
        public ResponseEntity<MemberDTO> changeProfile(MemberUpdateDTO member){
            return ResponseEntity.ok(service.update(member));
        }


        @PutMapping("/{email}/changepassword")
        public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
            service.changePassword(changePasswordDTO);
            return ResponseEntity.ok().build();
        }
    }
