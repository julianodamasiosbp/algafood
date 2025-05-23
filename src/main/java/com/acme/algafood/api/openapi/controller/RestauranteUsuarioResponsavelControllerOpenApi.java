package com.acme.algafood.api.openapi.controller;

import java.util.List;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.model.response.UsuarioModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

    @ApiOperation("Lista os responsáveis pelo restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exibe a lista de reponsáveis pelo restaurante", content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    public List<UsuarioModel> listar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId);

    @ApiOperation("Associa um responsável ao restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Responsável associada", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Responsável não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    public void associar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId,
            @ApiParam(value = "ID do usuario", required = true) Long usuarioId);

    @ApiOperation("Desassocia um responsável ao restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Responsável associado", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Responsável não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    public void desassociar(@ApiParam(value = "ID do restaurante", required = true) Long restauranteId,
            @ApiParam(value = "ID do usuario", required = true) Long usuarioId);
}
