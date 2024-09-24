package member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import security.account.domain.MemberVO;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private String userId; // user_id
    private String email; // email
    private Timestamp createAt; // createAt
   // updateAt
    private String userName; // user_name
    private MultipartFile avatar; // 아바타 파일
    private List<String> authList; // 권한 목록

    // MemberVO에서 MemberDTO로 변환
    public static MemberDTO of(MemberVO m) {
        return MemberDTO.builder()// userId
                .email(m.getEmail())
                .createAt(m.getCreateAt()) // createAt
               // updateAt 추가
                .userName(m.getUserName()) // userName
//                .authList(m.getAuthList().stream().map(a -> a.getAuth()).toList())
                .build();
    }

    // MemberDTO에서 MemberVO로 변환
    public MemberVO toVO() {
        return MemberVO.builder()
                .email(email)
                .createAt(createAt) // createAt에 해당
                .userName(userName) // user_name에 해당
                .build();
    }
}
