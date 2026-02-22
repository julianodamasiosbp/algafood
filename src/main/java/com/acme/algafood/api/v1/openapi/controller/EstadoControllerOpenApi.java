package com.acme.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.acme.algafood.api.v1.model.request.EstadoInput;
import com.acme.algafood.api.v1.model.response.EstadoModel;

public interface EstadoControllerOpenApi {

        CollectionModel<EstadoModel> listar();

        EstadoModel buscar(Long id);

        EstadoModel adicionar(EstadoInput estadoInput);

        EstadoModel atualizar(Long estadoId, EstadoInput estadoInput);

        void remover(Long id);

}
