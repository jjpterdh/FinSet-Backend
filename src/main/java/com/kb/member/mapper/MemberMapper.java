package com.kb.member.mapper;

import com.kb.member.dto.Auth;
import com.kb.member.dto.Member;

import java.util.List;


public interface MemberMapper {
    List<Member> selectMemberAll();
    Member selectById(String id);
    Member selectByName(String name);
    int insertMember(Member member);
    int insertAuth(Auth auth);
    int deleteAuth(Auth auth);
    int deleteMember(long uno);
}
