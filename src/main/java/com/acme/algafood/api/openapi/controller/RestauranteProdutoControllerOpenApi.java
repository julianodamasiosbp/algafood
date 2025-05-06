package com.acme.algafood.api.openapi.controller;

import java.util.List;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.model.request.ProdutoInput;
import com.acme.algafood.api.model.response.ProdutoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {

    @ApiOperation("Lista os produtos do restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Exibe a lista de reponsáveis pelo restaurante", content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    public List<ProdutoModel> listar(
            @ApiParam(value = "Indica se deve ou não incluir produtos inativos no resultado da listagem", example = "false", defaultValue = "false") boolean incluirInativos,
            @ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Busca um produto do restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    public ProdutoModel buscar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
            @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);

    @ApiOperation("Adiciona um produto ao restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto adicionado", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    public ProdutoModel adicionar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
            @ApiParam(name = "corpo", value = "Representação de um novo produto", required = true) ProdutoInput produtoInput);

    @ApiOperation("Atualiza um produto do restaurante")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto atualizado", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    public ProdutoModel atualizar(
            @ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,
            @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId,
            @ApiParam(name = "corpo", value = "Representação de um novo produto", required = true) ProdutoInput produtoInput);

}
