package com.kb.finance.mapper;

import com.kb.finance.dto.Deposit;

import java.util.List;

public interface DepositMapper {
    List<Deposit> selectAll();
    List<Deposit> selectSimple();
    List<Deposit> selectCompound();
    Deposit selectById(Long dno);
}
