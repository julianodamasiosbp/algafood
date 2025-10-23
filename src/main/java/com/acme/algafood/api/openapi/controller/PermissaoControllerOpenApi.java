package com.acme.algafood.api.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.acme.algafood.api.model.response.PermissaoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

    @ApiOperation("Lista as permissões")
    CollectionModel<PermissaoModel> listar();

}
