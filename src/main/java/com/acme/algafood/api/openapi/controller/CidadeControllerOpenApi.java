package com.acme.algafood.api.openapi.controller;

import java.util.List;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.model.request.CidadeInput;
import com.acme.algafood.api.model.response.CidadeModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

        @ApiOperation(value = "Lista as cidades")
        List<CidadeModel> listar();

        @ApiOperation("Busca uma cidade por ID")
        @ApiResponses({
                        @ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
                        @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
        })
        CidadeModel buscar(@ApiParam(value = "ID de uma cidade", required = true) Long cidadeId);

        @ApiOperation("Cadastra uma cidade")
        @ApiResponse(responseCode = "201", description = "Cidade criada", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
        CidadeModel adicionar(
                        @ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true) CidadeInput cidadeInput);

        @ApiOperation("Atualiza uma cidade por ID")
        @ApiResponse(responseCode = "200", description = "Cidade atualizada", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
        CidadeModel atualizar(@ApiParam(value = "ID de uma cidade", required = true) Long cidadeId,
                        @ApiParam(name = "corpo", value = "Representação de uma cidade com novos dados", required = true) CidadeInput cidadeInput);

        @ApiOperation("Exclui uma cidade por ID")
        @ApiResponses({
                        @ApiResponse(responseCode = "204", description = "Cidade excluída", content = @Content(schema = @Schema(implementation = Problem.class))),
                        @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
        })
        void remover(@ApiParam(value = "ID de uma cidade", required = true) Long cidadeId);

}
