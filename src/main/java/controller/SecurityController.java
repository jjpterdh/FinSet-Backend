package controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Log4j
@RequestMapping("/api/security")
@RestController
public class SecurityController {

    @GetMapping("/all")
    public ResponseEntity<String> doAll() {
        log.info("do all can access everybody");
        return ResponseEntity.ok("All can access everybody");
    }


    @GetMapping("/login")
    public void login() {
        log.info("login page");
    }
    @GetMapping("/logout")
    public void logout() {
        log.info("logout page");
    }

    @GetMapping("/member")
    public ResponseEntity<String> doMember(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info("username="+userDetails.getUsername());
        return ResponseEntity.ok(userDetails.getUsername());
    }

}
