package com.kb.member.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {
    private String id; 			// id(email)=username
    private String password;	// password
    private String name;        // 사용자이름
    private int type;
    private String kakaoId;

    public Member toMember() {
        return Member.builder().id(id).password(password).name(name).type(type).kakaoId(kakaoId).build();
    }
}
