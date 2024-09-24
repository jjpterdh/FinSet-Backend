package security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import security.account.domain.CustomUser;
import security.account.domain.MemberVO;
import security.account.mapper.UserDetailsMapper;

@Log4j
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDetailsMapper mapper;

@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
    MemberVO vo=mapper.get(email);
    if(vo==null){
        throw new UsernameNotFoundException(email+"은 없는 id 입니다");

    }
    return new CustomUser(vo);
}
}
