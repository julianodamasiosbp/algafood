package com.acme.algafood.domain.service;

import java.util.List;

import com.acme.algafood.domain.filter.VendaDiariaFilter;
import com.acme.algafood.domain.model.dto.VendaDiaria;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

}
