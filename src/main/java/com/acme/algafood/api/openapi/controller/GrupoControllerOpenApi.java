package com.acme.algafood.api.openapi.controller;

import java.util.List;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.model.request.GrupoInput;
import com.acme.algafood.api.model.response.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

        @ApiOperation(value = "Lista os grupos")
        List<GrupoModel> listar();

        @ApiOperation("Busca um grupo por ID")
        @ApiResponses({
                        @ApiResponse(responseCode = "400", description = "ID do grupo inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
                        @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        })
        GrupoModel buscar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);

        @ApiOperation("Cadastra um grupo")
        @ApiResponse(responseCode = "201", description = "Grupo criado", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        GrupoModel adicionar(
                        @ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true) GrupoInput grupoInput);

        @ApiOperation("Atualiza um grupo por ID")
        @ApiResponse(responseCode = "200", description = "Grupo atualizado", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        GrupoModel atualizar(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId,
                        @ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados", required = true) GrupoInput grupoInput);

        @ApiOperation("Exclui um grupo por ID")
        @ApiResponse(responseCode = "204", description = "Grupo excluído", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        void remover(@ApiParam(value = "ID de um grupo", example = "1", required = true) Long grupoId);
}
