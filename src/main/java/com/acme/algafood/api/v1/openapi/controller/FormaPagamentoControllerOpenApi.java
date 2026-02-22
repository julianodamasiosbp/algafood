package com.acme.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.acme.algafood.api.v1.model.request.FormaPagamentoInput;
import com.acme.algafood.api.v1.model.response.FormaPagamentoModel;

public interface FormaPagamentoControllerOpenApi {

	ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request);

	ResponseEntity<FormaPagamentoModel> buscar(Long formaPagamentoId, ServletWebRequest request);

	FormaPagamentoModel adicionar(FormaPagamentoInput formaPagamentoInput);

	FormaPagamentoModel atualizar(Long formaPagamentoId,FormaPagamentoInput formaPagamentoInput);

	ResponseEntity<Void> remover(Long formaPagamentoId);

}
