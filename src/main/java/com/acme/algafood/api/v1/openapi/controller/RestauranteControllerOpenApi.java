package com.acme.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.acme.algafood.api.v1.model.request.RestauranteInput;
import com.acme.algafood.api.v1.model.response.RestauranteApenasNomeModel;
import com.acme.algafood.api.v1.model.response.RestauranteBasicoModel;
import com.acme.algafood.api.v1.model.response.RestauranteModel;


public interface RestauranteControllerOpenApi {

  	CollectionModel<RestauranteBasicoModel> listar();

	CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();

	RestauranteModel buscar(Long restauranteId);

	RestauranteModel adicionar(RestauranteInput restauranteInput);

	RestauranteModel atualizar(Long restauranteId,RestauranteInput restauranteInput);

	ResponseEntity<Void> ativar(Long restauranteId);

	ResponseEntity<Void> inativar(Long restauranteId);

	ResponseEntity<Void> ativarMultiplos(List<Long> restauranteIds);

	ResponseEntity<Void> inativarMultiplos(List<Long> restauranteIds);

	ResponseEntity<Void> abrir(Long restauranteId);

	ResponseEntity<Void> fechar(Long restauranteId);

}
