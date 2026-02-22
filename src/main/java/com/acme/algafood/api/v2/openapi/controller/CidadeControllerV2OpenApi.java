package com.acme.algafood.api.v2.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.acme.algafood.api.v2.model.request.CidadeInputV2;
import com.acme.algafood.api.v2.model.response.CidadeModelV2;


public interface CidadeControllerV2OpenApi {

    CollectionModel<CidadeModelV2> listar();

    CidadeModelV2 buscar(Long cidadeId);

    CidadeModelV2 adicionar(CidadeInputV2 cidadeInputV2);

    CidadeModelV2 atualizar(Long cidadeId, CidadeInputV2 cidadeInputV2);

    void remover(Long cidadeId);

}
