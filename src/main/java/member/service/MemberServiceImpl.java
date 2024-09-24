package member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import member.dto.ChangePasswordDTO;
import member.dto.MemberDTO;
import member.dto.MemberJoinDTO;
import member.dto.MemberUpdateDTO;
import member.exception.PasswordMissmatchException;

import member.mapper.MemberMapper;

import security.account.domain.MemberVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final PasswordEncoder passwordEncoder;
    final MemberMapper mapper;

    @Override
    public boolean checkDuplicate(String username) {
        MemberVO member = mapper.findByUserId(username);
        return member != null ? true : false;
    }

    @Override
    public MemberDTO get(String username) {
        MemberVO member = Optional.ofNullable(mapper.get(username))
                .orElseThrow(NoSuchElementException::new);
        return MemberDTO.of(member);
    }



    @Transactional
    @Override
    public MemberDTO join(MemberJoinDTO dto) {
        MemberVO member = dto.toVO();

        member.setPassword(passwordEncoder.encode(member.getPassword()));
        mapper.insert(member);


        return get(member.getEmail());
    }

    @Override
    public MemberDTO update(MemberUpdateDTO member) {
        MemberVO vo=mapper.get(member.getEmail());
        if (!passwordEncoder.matches(member.getPassword(), vo.getPassword())) {
            throw new PasswordMissmatchException();
        }
        mapper.update(member.toVO());
        return get(member.getEmail());
    }

    @Override
    public void changePassword(ChangePasswordDTO changePassword) {
        MemberVO member=mapper.get(changePassword.getEmail());

        if (!passwordEncoder.matches(changePassword.getOldPassword(),member.getPassword())){
            throw new PasswordMissmatchException();
        }

        changePassword.setNewPassword(passwordEncoder.encode(changePassword.getNewPassword()));

        mapper.updatePassword(changePassword);
    }

}
