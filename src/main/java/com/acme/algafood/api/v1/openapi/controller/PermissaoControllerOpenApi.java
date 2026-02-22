package com.acme.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.acme.algafood.api.v1.model.response.PermissaoModel;

public interface PermissaoControllerOpenApi {

    CollectionModel<PermissaoModel> listar();

}
