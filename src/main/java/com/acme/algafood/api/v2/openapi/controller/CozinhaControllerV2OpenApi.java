package com.acme.algafood.api.v2.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.acme.algafood.api.v2.model.request.CozinhaInputV2;
import com.acme.algafood.api.v2.model.response.CozinhaModelV2;


public interface CozinhaControllerV2OpenApi {

    PagedModel<CozinhaModelV2> listar(Pageable pageable);

    CozinhaModelV2 buscar(Long cozinhaId);

    CozinhaModelV2 adicionar(CozinhaInputV2 cozinhaInput);

    CozinhaModelV2 atualizar(Long cozinhaId, CozinhaInputV2 cozinhaInput);

    void remover(Long cozinhaId);

}
