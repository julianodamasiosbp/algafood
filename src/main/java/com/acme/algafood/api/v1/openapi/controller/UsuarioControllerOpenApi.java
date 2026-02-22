package com.acme.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.acme.algafood.api.v1.model.request.SenhaInput;
import com.acme.algafood.api.v1.model.request.UsuarioInput;
import com.acme.algafood.api.v1.model.response.UsuarioModel;

public interface UsuarioControllerOpenApi {
 	CollectionModel<UsuarioModel> listar();

	UsuarioModel buscar(Long usuarioId);

	UsuarioModel adicionar(UsuarioInput usuarioInput);

	UsuarioModel atualizar(Long usuarioId,UsuarioInput usuarioInput);

	ResponseEntity<Void> alterarSenha(Long usuarioId,SenhaInput senha);

}
