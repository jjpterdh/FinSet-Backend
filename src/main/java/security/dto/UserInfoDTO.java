package security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.account.domain.MemberVO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private String username;
    private String email;


    public static UserInfoDTO of(MemberVO member) {
        return new UserInfoDTO(
                member.getUserName(),
                member.getEmail()
        );
    }
}
