package com.devcamp.tokofable.repository;

import com.devcamp.tokofable.entity.PaymentMethods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethods, String>, JpaSpecificationExecutor<PaymentMethods> {
}