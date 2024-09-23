package security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.account.domain.MemberVO;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private String username;
    private String email;
    private List<String> roles;

    public static UserInfoDTO of(MemberVO member) {
        return new UserInfoDTO(
                member.getUserName(), // userName에 맞춰 수정
                member.getEmail(),
                member.getAuthList().stream()
                        .map(auth -> auth.getAuth()) // auth가 있는지 확인
                        .collect(Collectors.toList()) // .toList() 대신 collect 사용
        );
    }
}
