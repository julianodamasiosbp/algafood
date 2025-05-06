package com.acme.algafood.api.openapi.controller;

import com.acme.algafood.api.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Pedidos")
public interface FluxoPedidoControllerOpenApi {

    @ApiOperation("Confirma um pedido pelo código")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pedido confirmado", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    public void confirmar(@ApiParam(value = "Código do pedido", required = true) String codigoPedido);

    @ApiOperation("Alterar o status do pedido para ENTREGUE pelo código")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pedido entregue", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    public void entregar(@ApiParam(value = "Código do pedido", required = true) String codigoPedido);

    @ApiOperation("Alterar o status do pedido para CANCELADO pelo código")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pedido cancelado", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    public void cancelar(@ApiParam(value = "Código do pedido", required = true) String codigoPedido);
}
