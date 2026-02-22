package com.acme.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.acme.algafood.api.v1.model.response.UsuarioModel;

public interface RestauranteUsuarioResponsavelControllerOpenApi {
	CollectionModel<UsuarioModel> listar(Long restauranteId);

	ResponseEntity<Void> desassociar(Long restauranteId,Long usuarioId);

	ResponseEntity<Void> associar(Long restauranteId,Long usuarioId);
}
