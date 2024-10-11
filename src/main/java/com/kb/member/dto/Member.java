package com.kb.member.dto;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Member implements UserDetails {
    private long uno;			// PK
    private String id; 			// id(email)=username
    private String password;	// password
    private String name;        // 사용자이름
    private int type;           // 투자 성향 타입
    private int status;
   // 활성화 여부, 1, 0
    private String createdAt;    // 생성일
    private String statusDate;    // 수정일
    private String kakaoId;
    private String token; // JWT 토큰값, DB로는 저장하지 않음

    // 복수개의 권한을 관리
    // 문자열data("ROLE_USER", "ROLE_ADMIN")를 처리할 수 있는 GrantedAuthority의 하위클래스
    private List<SimpleGrantedAuthority> authorities; // authorities

    /**
     * Collection - List/Set
     *
     * Collection<? extends GrantedAuthority>
     * 	- <GrantedAuthority를 상속하는 ?> -> 자식타입(상한선)
     *  - <? super Integer> -> Integer 부모타입 (하한선)
     * Collection<GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String setUsername(String username) {
        return this.id = username;
    }
    @Override
    public String getUsername() {
        return id;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == 1;
    }

    public boolean checkRequiredValue(){
        try {
            return (id.isEmpty() || password.isEmpty() || name.isEmpty());
        }catch (Exception e){
            return false;
        }
    }

}
