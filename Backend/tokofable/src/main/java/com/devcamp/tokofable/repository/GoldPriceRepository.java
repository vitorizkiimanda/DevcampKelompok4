package com.devcamp.tokofable.repository;

import com.devcamp.tokofable.entity.GoldPrices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface GoldPriceRepository extends JpaRepository<GoldPrices, String>, JpaSpecificationExecutor<GoldPrices> {
    GoldPrices findByDate(Date date);
}