package org.scoula.board.mapper;

import board.mapper.BoardMapper;
import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import board.domain.BoardVO;
import config.RootConfig;
import security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, SecurityConfig.class})
@Log4j
class BoardMapperTest {
    @Autowired
    BoardMapper mapper;
    @Test

    void testPage() {
        for (int i=0; i<100; i++) {
            BoardVO board = BoardVO.builder()
                    .title("페이징 테슽트"+i)
                    .writer("user0")
                    .content("페이징 테스트")
                    .build();
            mapper.create(board);
        }
    }
}