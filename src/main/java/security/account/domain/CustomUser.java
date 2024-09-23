package security.account.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomUser extends User {
    private MemberVO member;

    public CustomUser(String email, String password,
                      Collection<? extends GrantedAuthority> authorities) {
        super(email,password,authorities);
    }

    public CustomUser(security.account.domain.MemberVO vo) {
        super(vo.getEmail(),vo.getPassword(),vo.getAuthList());
        this.member=vo;
    }

}
