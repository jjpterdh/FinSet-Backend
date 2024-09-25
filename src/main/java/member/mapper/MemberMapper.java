package member.mapper;

import member.dto.Member;

public interface MemberMapper {
    Member getMember(int uno);

    int insertMember(Member member);
//    int update(Member member);

    Member selectByEmail(String id);
    Member findByUserName(String username);
    Member findByUserEmail(String email);

}
