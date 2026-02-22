package com.acme.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.v1.model.request.GrupoInput;
import com.acme.algafood.api.v1.model.response.GrupoModel;

public interface GrupoControllerOpenApi {

 	CollectionModel<GrupoModel> listar();

	GrupoModel buscar(Long grupoId);

	GrupoModel adicionar(GrupoInput grupoInput);

	GrupoModel atualizar(Long grupoId,GrupoInput grupoInput);

	ResponseEntity<Void> remover(Long grupoId);
}
