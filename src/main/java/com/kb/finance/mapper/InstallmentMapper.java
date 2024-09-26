package com.kb.finance.mapper;

import com.kb.finance.dto.Installment;

import java.util.List;

public interface InstallmentMapper {
    List<Installment> selectAll();
    Installment selectById(Long ino);
}
