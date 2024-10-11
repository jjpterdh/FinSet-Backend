package com.kb.member.service;

import com.kb.member.dto.Auth;
import com.kb.member.dto.MemberTypeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.kb.member.dto.Member;
import com.kb.member.exception.PasswordMissmatchException;
import com.kb.member.mapper.MemberMapper;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService{

    final PasswordEncoder passwordEncoder;
    final MemberMapper mapper;


//    public Member login(Member member) {
//        Member saveMember = mapper.selectById(member.getId());
//        if(passwordEncoder.matches(member.getPassword(), saveMember.getPassword())) {
//            saveMember.setPassword("");
//            saveMember.setUno(0);
//            return saveMember;
//        }else{
//            return null;
//        }
//    }

    public boolean checkEmailDuplicate(String id) {
        Member member = mapper.selectById(id);
        return member != null ? true : false;
    }

    public boolean checkNameDuplicate(String name) {
        Member member = mapper.selectByName(name);
        return member != null ? true : false;
    }
    public Member getMemberByKakaoId(String id) {
        return Optional.ofNullable(mapper.selectBykakaoId(id))
                .orElseThrow(NoSuchElementException::new);
    }
    public Member getMember(String id) {
        return Optional.ofNullable(mapper.selectById(id))
                        .orElseThrow(NoSuchElementException::new);
    }

    public MemberTypeDTO getType(String id) {
        return Optional.ofNullable(mapper.selectType(id))
                .orElseThrow(NoSuchElementException::new);
    }

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
        Auth auth = new Auth(member.getId(), "ROLE_MEMBER");
        result = mapper.insertAuth(auth);
        if(result != 1){
            throw new IllegalAccessException();
        }
        return mapper.selectById(member.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    public Member deleteMember(String id) {
        Member member = mapper.selectById(id);
        if(member == null){
            throw new NoSuchElementException();
        }
         mapper.deleteMember(member.getUno());
        return member;
    }

    @Transactional(rollbackFor = Exception.class)
    public Member updateName(String id, String name) {
        log.info("start update...");

        Member member = mapper.selectById(id);
        if(member == null){
            throw new NoSuchElementException();
        }
        mapper.updateName(id, name);
        return member;
    }

    @Transactional(rollbackFor = Exception.class)
    public Member updateType(String id, int type) {
        log.info("start update...");

        Member member = mapper.selectById(id);
        if(member == null){
            throw new NoSuchElementException();
        }
        mapper.updateType(id, type);
        return member;
    }
}