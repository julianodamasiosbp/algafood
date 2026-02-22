package com.acme.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.acme.algafood.api.v1.model.response.GrupoModel;

public interface UsuarioGrupoControllerOpenApi {

	CollectionModel<GrupoModel> listar(Long usuarioId);

	ResponseEntity<Void> desassociar(Long usuarioId,Long grupoId);

	ResponseEntity<Void> associar(Long usuarioId,Long grupoId);

}
