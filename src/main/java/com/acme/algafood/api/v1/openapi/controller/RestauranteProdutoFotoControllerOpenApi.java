package com.acme.algafood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.acme.algafood.api.v1.model.request.FotoProdutoInput;
import com.acme.algafood.api.v1.model.response.FotoProdutoModel;

public interface RestauranteProdutoFotoControllerOpenApi {

	FotoProdutoModel atualizarFoto(Long restauranteId, Long produtoId, FotoProdutoInput fotoProdutoInput,
								   MultipartFile arquivo) throws IOException;

	ResponseEntity<Void> excluir(Long restauranteId,Long produtoId);

	FotoProdutoModel buscar(Long restauranteId,Long produtoId);

	ResponseEntity<?> servir(Long restauranteId, Long produtoId, String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;

}
