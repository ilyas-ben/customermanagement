package com.ilouse.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FraudCheckServiceImpl implements FraudCheckService {

    private final FraudCheckHistoryRepo fraudCheckHistoryRepo;

    @Override
    public boolean isFraudulentCustomer(Integer customerID) {
        fraudCheckHistoryRepo.save(
                FraudCheckHistory.builder().
                        customerId(customerID)
                        .isFraudster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return true;
    }
}