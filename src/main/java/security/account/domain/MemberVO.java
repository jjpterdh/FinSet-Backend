package security.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberVO {
    private int uno;
    private String email;
    private String password;
    private String userName;
    private String userPosition;
    private Character userStatus;
    private Timestamp createdAt;
    private List<AuthVO> authList;
}
