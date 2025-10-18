package com.acme.algafood.api.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.model.request.PedidoInput;
import com.acme.algafood.api.model.response.PedidoModel;
import com.acme.algafood.api.model.response.PedidoResumoModel;
import com.acme.algafood.domain.filter.PedidoFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

        @ApiOperation(value = "Lista os pedidos")
        @ApiImplicitParams({
                        @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula", name = "campos", paramType = "query", type = "string")
        })
        @GetMapping
        PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, Pageable pageable);

        @ApiOperation("Busca um pedido por código")
        @ApiResponses({
                        @ApiResponse(responseCode = "400", description = "ID do pedido inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
                        @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        })
        @ApiImplicitParams({
                        @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula", name = "campos", paramType = "query", type = "string")
        })
        PedidoModel buscar(
                        @ApiParam(value = "Código de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true) String codigoPedido);

        @ApiOperation("Adiciona um pedido")
        @ApiResponse(responseCode = "201", description = "Pedido criado", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "PEdido não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        PedidoModel adicionar(
                        @ApiParam(name = "corpo", value = "Representação de um novo pedido", required = true) PedidoInput pedidoInput);

}
