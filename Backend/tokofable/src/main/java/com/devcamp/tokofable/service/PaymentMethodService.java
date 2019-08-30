package com.devcamp.tokofable.service;

import com.devcamp.tokofable.entity.PaymentMethods;
import com.devcamp.tokofable.exceptions.BadRequestException;
import com.devcamp.tokofable.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository ;

    public PaymentMethods create(PaymentMethods paymentMethods){
        validate(paymentMethods);
        paymentMethods.setId(UUID.randomUUID().toString());
        return paymentMethodRepository.save(paymentMethods);
    }

    private void validate(PaymentMethods paymentMethods){
        if(StringUtils.isEmpty(paymentMethods.getName()))
            throw new BadRequestException("Nama harus di isi");
    }
}
