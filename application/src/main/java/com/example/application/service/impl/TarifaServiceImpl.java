package com.example.application.service.impl;

import com.example.persistence.entities.Brand;
import com.example.persistence.entities.Price;
import com.example.persistence.entities.Product;
import com.example.persistence.repo.BrandRepository;
import com.example.persistence.repo.PriceRepository;
import com.example.persistence.repo.ProductRepository;
import com.example.application.dto.TarifaDto;
import com.example.application.service.TarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TarifaServiceImpl implements TarifaService {

    @Autowired
    private PriceRepository priceRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private BrandRepository brandRepo;

    @Override
    public TarifaDto findTarifaByFechaProductBrand(LocalDateTime fecha, Long idProduct, Long idBrand) {

        // Search Product
        Product product = productRepo.getReferenceById(idProduct);

        // Search Brand
        Brand brand = brandRepo.getReferenceById(idBrand);

        // Search for Price in fecha
        Price price = priceRepo.getPriceByDateProductBrand(fecha, product, brand);

        // Populate DTO
        TarifaDto tarifa = new TarifaDto();
        tarifa.setStartDate(price.getStartDate());
        tarifa.setEndDate(price.getEndDate());
        tarifa.setIdBrand(brand.getIdBrand());
        tarifa.setIdProduct(product.getIdProduct());
        tarifa.setPriceList(price.getPriceList());
        tarifa.setPrice(price.getRate());

        return tarifa;
    }
}
