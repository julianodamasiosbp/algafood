package com.acme.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.acme.algafood.api.v1.model.request.CozinhaInput;
import com.acme.algafood.api.v1.model.response.CozinhaModel;
import com.acme.algafood.domain.model.Cozinha;

public interface CozinhaControllerOpenApi {

        PagedModel<CozinhaModel> listar(Pageable pageable);

        CozinhaModel buscar(Long cozinhaId);

        Cozinha adicionar(CozinhaInput cozinhaInput);

        Cozinha atualizar(Long cozinhaId, CozinhaInput cozinhaInput);

        void remover(Long cozinhaId);

}