package security.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp; // createAt을 Timestamp로 변경
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberVO {
    private int uno; // 자동 증가 키
    private String email; // user_id
    private String password;
    private String userName;
    private String user_position;
    private Character user_status;
    private Timestamp createAt; // createAt
    private List<AuthVO> authList;
}
