package com.acme.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.acme.algafood.api.v1.controller.EstatisticasController.EstatisticasModel;
import com.acme.algafood.domain.filter.VendaDiariaFilter;
import com.acme.algafood.domain.model.dto.VendaDiaria;

public interface EstatisticasControllerOpenApi {

	EstatisticasModel estatisticas();

	List<VendaDiaria> consultarVendasDiarias(
			VendaDiariaFilter filtro,String timeOffset);

	ResponseEntity<byte[]> consultarVendasDiariasPdf(
			VendaDiariaFilter filtro,
			String timeOffset);

}
