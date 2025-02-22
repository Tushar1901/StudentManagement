package com.StdMngmnt.service;

import com.StdMngmnt.entity.Fee;
import com.StdMngmnt.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeService {

        @Autowired
        private FeeRepository feeRepository;
        public List<Fee> getAllFees() { return feeRepository.findAll(); }
        public Fee saveFee(Fee fee) { return feeRepository.save(fee); }
}
