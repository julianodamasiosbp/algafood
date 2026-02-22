package com.acme.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.acme.algafood.api.v1.model.response.FormaPagamentoModel;

public interface RestauranteFormaPagamentoControllerOpenApi {

	CollectionModel<FormaPagamentoModel> listar(Long restauranteId);

	ResponseEntity<Void> desassociar(Long restauranteId,Long formaPagamentoId);

	ResponseEntity<Void> associar(Long restauranteId,Long formaPagamentoId);

}
