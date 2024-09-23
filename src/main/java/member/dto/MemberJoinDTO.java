package member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;
import security.account.domain.MemberVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class MemberJoinDTO {
    String email;
    String password;
    String userName;

    MultipartFile avatar;

    public MemberVO toVO(){
        return MemberVO.builder()
                .email(email)
                .password(password)
                .userName(userName)
                .build();
    }
}
