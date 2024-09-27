package com.kb.finance.service;

import com.kb.finance.dto.Installment;
import com.kb.finance.mapper.InstallmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor

public class InstallmentService {
    final InstallmentMapper mapper;

    public List<Installment> getAllInstallments() {
        List<Installment> list = mapper.selectAll();

        if(list.isEmpty()) {
            throw new NoSuchElementException();
        }
        return list;
    }


    public List<Installment> getSimpleInstallments() {
        List<Installment> list = mapper.selectSimple();
        if(list.isEmpty()) {
            throw new NoSuchElementException();
        }
        return list;
    }

    public List<Installment> getCompoundInstallments() {
        List<Installment> list = mapper.selectCompound();
        if(list.isEmpty()) {
            throw new NoSuchElementException();
        }
        return list;
    }

    public Installment getInstallment(Long id) {
        return Optional.ofNullable(mapper.selectById(id))
                .orElseThrow(NoSuchElementException::new);
    }
}
