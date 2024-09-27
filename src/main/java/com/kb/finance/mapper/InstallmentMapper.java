package com.kb.finance.mapper;

import com.kb.finance.dto.Installment;

import java.util.List;

public interface InstallmentMapper {
    List<Installment> selectAll();
    List<Installment> selectSimple();
    List<Installment> selectCompound();
    Installment selectById(Long ino);
}
