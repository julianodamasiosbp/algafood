package com.acme.algafood.api.openapi.controller;

import java.util.List;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.model.request.GrupoInput;
import com.acme.algafood.api.model.response.GrupoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

    @ApiOperation(value = "Lista os grupos")
    public List<GrupoModel> listar();

    @ApiOperation("Busca um grupo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do grupo inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    public GrupoModel buscar(Long grupoId);

    @ApiOperation("Cadastra um grupo")
    @ApiResponse(responseCode = "201", description = "Grupo criado", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public GrupoModel adicionar(GrupoInput grupoInput);

    @ApiOperation("Atualiza um grupo por ID")
    @ApiResponse(responseCode = "200", description = "Grupo atualizado", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public GrupoModel atualizar(Long grupoId, GrupoInput grupoInput);

    @ApiOperation("Exclui um grupo por ID")
    @ApiResponse(responseCode = "204", description = "Grupo excluído", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void remover(Long grupoId);
}
