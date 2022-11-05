package com.example.persistence.repo;

import com.example.persistence.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository
    extends JpaRepository<Price, Long>, CustomPriceRepository {
}
