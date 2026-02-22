package com.acme.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.acme.algafood.api.v1.model.request.ProdutoInput;
import com.acme.algafood.api.v1.model.response.ProdutoModel;

public interface RestauranteProdutoControllerOpenApi {

	CollectionModel<ProdutoModel> listar(Long restauranteId,Boolean incluirInativos);

	ProdutoModel buscar(Long restauranteId,Long produtoId);

	ProdutoModel adicionar(Long restauranteId,ProdutoInput produtoInput);

	ProdutoModel atualizar(Long restauranteId,Long produtoId,ProdutoInput produtoInput);

}
