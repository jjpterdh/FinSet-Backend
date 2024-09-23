package security.account.mapper;

import security.account.domain.MemberVO;

public interface UserDetailsMapper {
    public MemberVO get(String username);
}
