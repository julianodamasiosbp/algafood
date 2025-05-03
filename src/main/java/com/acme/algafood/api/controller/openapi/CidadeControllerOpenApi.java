package com.acme.algafood.api.controller.openapi;

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
    public List<CidadeModel> listar();

    @ApiOperation("Busca uma cidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    public CidadeModel buscar(@ApiParam("ID de uma cidade") Long cidadeId);

    @ApiOperation("Cadastra uma cidade")
    @ApiResponse(responseCode = "201", description = "Cidade criada", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public CidadeModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma nova cidade") CidadeInput cidadeInput);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponse(responseCode = "200", description = "Cidade atualizada", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    public CidadeModel atualizar(@ApiParam("ID de uma cidade") Long cidadeId,
            @ApiParam(name = "corpo", value = "Representação de uma cidade com novos dados") CidadeInput cidadeInput);

    @ApiOperation("Exclui uma cidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cidade excluída", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    public void remover(@ApiParam("ID de uma cidade") Long cidadeId);

}
