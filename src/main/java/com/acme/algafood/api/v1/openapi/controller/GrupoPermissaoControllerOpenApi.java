package com.acme.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.acme.algafood.api.v1.model.response.PermissaoModel;


public interface GrupoPermissaoControllerOpenApi {

	CollectionModel<PermissaoModel> listar(Long grupoId);

	ResponseEntity<Void> desassociar(Long grupoId,Long permissaoId);

	ResponseEntity<Void> associar(Long grupoId,Long permissaoId);
}
