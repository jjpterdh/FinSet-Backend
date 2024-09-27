package com.kb.finance.service;


import com.kb.finance.dto.Deposit;
import com.kb.finance.mapper.DepositMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepositService {

    final DepositMapper mapper;

    public List<Deposit> getAllDeposits() {
        List<Deposit> depositList = mapper.selectAll();
        log.info("depositList: {}", depositList);
        if(depositList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return depositList;
    }

    public List<Deposit> getSimpleDeposits() {
        List<Deposit> depositList = mapper.selectSimple();
        if(depositList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return depositList;
    }

    public List<Deposit> getCompoundDeposits() {
        List<Deposit> depositList = mapper.selectCompound();
        if(depositList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return depositList;
    }

    public Deposit getDeposit(Long id) {
        return Optional.ofNullable(mapper.selectById(id))
                .orElseThrow(NoSuchElementException::new);
    }
}
