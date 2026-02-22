package com.acme.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;
import com.acme.algafood.api.v1.model.request.CidadeInput;
import com.acme.algafood.api.v1.model.response.CidadeModel;
import org.springframework.http.ResponseEntity;

public interface CidadeControllerOpenApi {

        CollectionModel<CidadeModel> listar();

        CidadeModel buscar(Long cidadeId);

        CidadeModel adicionar(CidadeInput cidadeInput);

        CidadeModel atualizar(Long cidadeId, CidadeInput cidadeInput);

        ResponseEntity<Void> remover(Long cidadeId);

}