package org.scoula.security.util;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import config.RootConfig;
import security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import security.util.JwtProcessor;

import static org.junit.jupiter.api.Assertions.*;
@Log4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
class JwtProcessorTest {
@Autowired
JwtProcessor jwtProcessor;
    @Test
    void generateToken() {
        String username = "user0";
        String token = jwtProcessor.generateToken(username);
      log.info(token);
        assertNotNull(token);

    }

    @Test
    void getUsername() {
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTcyNDIwNTMzMCwiZXhwIjoxNzI0MjA1NjMwfQ.FmoWNzcxmZ3RJ024GXE1y5oN9-XQPYvgArEXX9a2o76tt2QXL5kHHvSj-MQ1TwBl";
        String username = jwtProcessor.getUsername(token);
        log.info(username);
        assertNotNull(username);
    }

    @Test
    void validateToken() {
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTcyNDIwNTMzMCwiZXhwIjoxNzI0MjA1NjMwfQ.FmoWNzcxmZ3RJ024GXE1y5oN9-XQPYvgArEXX9a2o76tt2QXL5kHHvSj-MQ1TwBl";
        boolean isValid = jwtProcessor.validateToken(token);
        log.info(isValid);
        assertTrue(isValid);

    }
}