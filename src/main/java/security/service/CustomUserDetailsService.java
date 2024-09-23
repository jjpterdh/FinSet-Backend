package security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import security.account.domain.CustomUser;
import security.account.domain.MemberVO;
import security.account.mapper.UserDetailsMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Log4j
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserDetailsMapper mapper;

@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    MemberVO vo=mapper.get(username);
    if(vo==null){
        throw new UsernameNotFoundException(username+"은 없는 id 입니다");

    }
    return new CustomUser(vo);
}
}
