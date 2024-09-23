package member.mapper;

import member.dto.ChangePasswordDTO;
import security.account.domain.AuthVO;
import security.account.domain.MemberVO;
import security.account.domain.AuthVO;

public interface MemberMapper {
    MemberVO get(String name);
    MemberVO findByUsername(String username);

    int insert(MemberVO member);
    int insertAuth(AuthVO auth);
    int update(MemberVO member);
    int updatePassword(ChangePasswordDTO changePasswordDTO);
}
