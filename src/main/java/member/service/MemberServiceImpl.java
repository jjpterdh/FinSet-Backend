package member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import member.dto.Member;

import member.mapper.MemberMapper;

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
    public boolean checkEmailDuplicate(String email) {
        Member member = mapper.findByUserEmail(email);
        return member != null ? true : false;
    }

    @Override
    public boolean checkNameDuplicate(String name) {
        Member member = mapper.findByUserName(name);
        return member != null ? true : false;
    }

    @Override
    public Member getMember(int uno) {
        return Optional.ofNullable(mapper.getMember(uno))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Member join(Member member) throws IllegalAccessException {
        if(member.checkRequiredValue()){
            throw new IllegalAccessException();
        }
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        int result = mapper.insertMember(member);
        if(result != 1){
            throw new IllegalAccessException();
        }
        return mapper.selectByEmail(member.getEmail());
    }

    public Member login(Member member) {
        Member saveMember = mapper.selectByEmail(member.getEmail());
        if(passwordEncoder.matches(member.getPassword(), saveMember.getPassword())) {
            saveMember.setPassword("");
            saveMember.setUno(0);
            return saveMember;
        }else{
            return null;
        }
    }

}
