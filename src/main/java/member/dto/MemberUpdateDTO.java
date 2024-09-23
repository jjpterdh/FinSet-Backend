package member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateDTO {
    private String userId;
    private String password;
    private String email;

    MultipartFile avatar;

    public MemberVO toVO() {
        return MemberVO.builder()
                .userId(userId)
                .email(email)
                .build();

    }
}
