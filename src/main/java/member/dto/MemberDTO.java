package member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.account.domain.MemberVO;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private String email;
    private Timestamp createdAt;
    private String userName;

    public static MemberDTO of(MemberVO m) {
        return MemberDTO.builder()
                .email(m.getEmail())
                .createdAt(m.getCreatedAt())

                .userName(m.getUserName())
                .build();
    }


    public MemberVO toVO() {
        return MemberVO.builder()
                .email(email)
                .createdAt(createdAt)
                .userName(userName)
                .build();
    }
}
