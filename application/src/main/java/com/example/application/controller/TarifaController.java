package com.example.application.controller;

import com.example.persistence.entities.Brand;
import com.example.persistence.repo.BrandRepository;
import com.example.application.dto.TarifaDto;
import com.example.application.service.TarifaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
@Slf4j
public class TarifaController {

    @Autowired
    TarifaService tarifaService;

    @Autowired
    BrandRepository brandRepo;

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getAllBrands(){
            List<Brand> brands = new ArrayList<>();
            brandRepo.findAll().forEach(brands::add);

            if(brands.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            log.info("Brands ready to be served.");

            return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/brands/{brandId}/products/{productId}/tarifa")
    public TarifaDto getTarifaOnDate(
            @PathVariable Long brandId,
            @PathVariable Long productId,
            @RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime date) {
        log.info("Rate requested for brandID {}, productId {} and date {}",brandId,productId,date);
        return tarifaService.findTarifaByFechaProductBrand(date,productId,brandId);
    }
}
