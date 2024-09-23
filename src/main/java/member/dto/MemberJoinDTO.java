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
    String userId;
    String password;
    String email;

    MultipartFile avatar;

    public MemberVO toVO(){
        return MemberVO.builder()
                .userId(userId)
                .password(password)
                .email(email)
                .build();
    }
}
