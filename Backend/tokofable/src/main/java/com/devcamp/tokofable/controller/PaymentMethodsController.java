package com.devcamp.tokofable.controller;

import com.devcamp.tokofable.entity.PaymentMethods;
import com.devcamp.tokofable.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment-method")
public class PaymentMethodsController {
    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping
    public PaymentMethods create(@RequestBody PaymentMethods paymentMethods) {
        return paymentMethodService.create(paymentMethods);
    }
}
