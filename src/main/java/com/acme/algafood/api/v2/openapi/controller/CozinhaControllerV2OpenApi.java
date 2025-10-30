package com.acme.algafood.api.v2.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.v2.model.request.CozinhaInputV2;
import com.acme.algafood.api.v2.model.response.CozinhaModelV2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerV2OpenApi {
    @ApiOperation("Lista as cozinhas com paginação")
    PagedModel<CozinhaModelV2> listar(Pageable pageable);

    @ApiOperation("Busca uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cozinha inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    CozinhaModelV2 buscar(
            @ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

    @ApiOperation("Cadastra uma cozinha")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cozinha criada", content = @Content(schema = @Schema(implementation = CozinhaModelV2.class))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    CozinhaModelV2 adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true) CozinhaInputV2 cozinhaInput);

    @ApiOperation("Atualiza uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cozinha atualizada", content = @Content(schema = @Schema(implementation = CozinhaModelV2.class))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    CozinhaModelV2 atualizar(
            @ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId,
            @ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados", required = true) CozinhaInputV2 cozinhaInput);

    @ApiOperation("Exclui uma cozinha por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cozinha excluída", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    void remover(
            @ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

}
