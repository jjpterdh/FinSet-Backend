package com.kb.member.mapper;

import com.kb.member.dto.Auth;
import com.kb.member.dto.Member;
import com.kb.member.dto.MemberTypeDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MemberMapper {
    List<Member> selectMemberAll();
    Member selectById(String id);
    Member selectByName(String name);
    int insertMember(Member member);
    int insertAuth(Auth auth);
    int deleteAuth(Auth auth);
    int deleteMember(long uno);
    int updateName(@Param("id") String id, @Param("name") String name);
    int updateType(@Param("id") String id, @Param("type") int type);
    MemberTypeDTO selectType(String id);
    Member selectBykakaoId(String kakaoId);

}
