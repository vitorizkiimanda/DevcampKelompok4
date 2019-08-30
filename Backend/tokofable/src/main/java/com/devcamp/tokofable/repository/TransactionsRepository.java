package com.devcamp.tokofable.repository;

import com.devcamp.tokofable.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions, String>, JpaSpecificationExecutor<Transactions> {
}