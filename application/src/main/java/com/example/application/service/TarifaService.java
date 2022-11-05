package com.example.application.service;

import com.example.application.dto.TarifaDto;

import java.time.LocalDateTime;

public interface TarifaService {

    TarifaDto findTarifaByFechaProductBrand(LocalDateTime fecha, Long idProduct, Long id);
}
