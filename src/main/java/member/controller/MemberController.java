    package member.controller;

    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import common.util.UploadFiles;
    import member.dto.ChangePasswordDTO;

    import member.dto.MemberDTO;
    import member.dto.MemberJoinDTO;
    import member.dto.MemberUpdateDTO;
    import member.service.MemberService;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import javax.servlet.http.HttpServletResponse;
    import java.io.File;

    @Slf4j
    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/api/member")
    public class MemberController {
        final MemberService service;

        @GetMapping("/checkusername/{username}")
        public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
            return ResponseEntity.ok().body(service.checkDuplicate(username));
        }
        @PostMapping("")
        public ResponseEntity<MemberDTO> join(MemberJoinDTO member) {
//        회원 가입 처리하고 가입된 회원 정보를 ResponseEntity로 반환
            return ResponseEntity.ok(service.join(member));
        }
        @PutMapping("/{username}")
        public ResponseEntity<MemberDTO> changeProfile(MemberUpdateDTO member){
            return ResponseEntity.ok(service.update(member));
        }

        @GetMapping("/{username}/avatar")
        public void getAvatar(@PathVariable String username, HttpServletResponse response){
            String avatarPath="c:/upload/avatar/"+username+".png";
            File file=new File(avatarPath);
            if (!file.exists()){
                file=new File("C:/upload/avatar/unknown.png");
            }
            UploadFiles.downloadImage(response,file);
        }
        @PutMapping("/{username}/changepassword")
        public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
            service.changePassword(changePasswordDTO);
            return ResponseEntity.ok().build();
        }
    }
