package member.service;

import member.dto.ChangePasswordDTO;
import member.dto.MemberDTO;
import member.dto.MemberJoinDTO;
import member.dto.MemberUpdateDTO;

public interface MemberService {
    boolean checkDuplicate(String username);
    MemberDTO get(String username);
    MemberDTO join(MemberJoinDTO member);
    MemberDTO update(MemberUpdateDTO member);
    void changePassword(ChangePasswordDTO changePasswordDTO);
}
