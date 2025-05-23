package com.acme.algafood.api.openapi.controller;

import java.util.List;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.model.response.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {

    @ApiOperation("Lista as formas de pagamento do restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exibe a lista de formas de pagamento", content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    public List<FormaPagamentoModel> listar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId);

    @ApiOperation("Associa uma forma de pagamento ao restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Forma de pagamento associada", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    public void associar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId,
            @ApiParam(value = "ID da forma de pagamente", required = true) Long formaPagamentoId);

    @ApiOperation("Desassocia uma forma de pagamento ao restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Forma de pagamento desassociada", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    public void desassociar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId,
            @ApiParam(value = "ID da forma de pagamente", required = true) Long formaPagamentoId);

}
