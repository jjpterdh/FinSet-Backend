package org.scoula.security.mapper;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import config.RootConfig;
import security.account.domain.AuthVO;
import security.account.domain.MemberVO;
import security.account.mapper.UserDetailsMapper;
import security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@Log4j

public class UserDetailsMapperTest {
@Autowired
private UserDetailsMapper mapper;

@Test
    public void testGet() {
    MemberVO member=mapper.get("admin");
    log.info(member);

    for (AuthVO auth : member.getAuthList()) {
        log.info(auth);
    }
}


}
