package member.service;

import member.dto.Member;
import member.dto.MemberDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface MemberService {
    boolean checkEmailDuplicate(String email);
    boolean checkNameDuplicate(String username);
    Member getMember(int uno);

    @Transactional(rollbackFor = Exception.class)
    Member join(Member member) throws IllegalAccessException;


//    Member update(MemberDTO member);
}
