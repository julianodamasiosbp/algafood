package com.acme.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.acme.algafood.api.v1.model.request.PedidoInput;
import com.acme.algafood.api.v1.model.response.PedidoModel;
import com.acme.algafood.api.v1.model.response.PedidoResumoModel;
import com.acme.algafood.domain.filter.PedidoFilter;

public interface PedidoControllerOpenApi {

	PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);

	PedidoModel adicionar(PedidoInput pedidoInput);

	PedidoModel buscar(String codigoPedido);

}
