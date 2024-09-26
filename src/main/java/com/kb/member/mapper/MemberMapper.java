package com.kb.member.mapper;

import com.kb.member.dto.Auth;
import com.kb.member.dto.Member;

import java.util.List;

public interface MemberMapper {
    List<Member> selectMemberAll();
    Member selectById(String id);
    int insertMember(Member member);
    int insertAuth(Auth auth);
    int deleteAuth(Auth auth);
}
