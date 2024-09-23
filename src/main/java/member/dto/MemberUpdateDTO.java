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

    private String password;
    private String email;
    private String userName;

    MultipartFile avatar;

    public MemberVO toVO() {
        return MemberVO.builder()
                .email(email)
                .userName(userName)
                .build();

    }
}
