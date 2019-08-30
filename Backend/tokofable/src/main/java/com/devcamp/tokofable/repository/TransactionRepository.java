package com.devcamp.tokofable.repository;

import com.devcamp.tokofable.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, String>, JpaSpecificationExecutor<Transactions> {
    List<Transactions> findByUser_Id(String id);
}