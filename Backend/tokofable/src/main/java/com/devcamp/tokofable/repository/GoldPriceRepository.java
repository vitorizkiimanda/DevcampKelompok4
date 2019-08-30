package com.devcamp.tokofable.repository;

import com.devcamp.tokofable.entity.GoldPrices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GoldPriceRepository extends JpaRepository<GoldPrices, String>, JpaSpecificationExecutor<GoldPrices> {
}