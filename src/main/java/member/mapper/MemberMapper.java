package member.mapper;

import member.dto.ChangePasswordDTO;
import security.account.domain.MemberVO;

public interface MemberMapper {
    MemberVO get(String name);
    MemberVO findByUserId(String email);

    int insert(MemberVO member);
//    int insertAuth(AuthVO auth);
    int update(MemberVO member);
    int updatePassword(ChangePasswordDTO changePasswordDTO);
}
