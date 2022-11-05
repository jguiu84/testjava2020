package com.example.persistence.repo;

import com.example.persistence.entities.Brand;
import com.example.persistence.entities.Price;
import com.example.persistence.entities.Product;

import java.time.LocalDateTime;

public interface CustomPriceRepository {
    Price getPriceByDateProductBrand(LocalDateTime dateTime, Product product, Brand brand);
}
