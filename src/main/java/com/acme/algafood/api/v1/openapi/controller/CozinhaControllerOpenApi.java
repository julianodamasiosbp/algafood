package com.acme.algafood.api.v1.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.v1.model.request.CozinhaInput;
import com.acme.algafood.api.v1.model.response.CozinhaModel;
import com.acme.algafood.domain.model.Cozinha;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

        @ApiOperation(value = "Lista as cozinhas")
        PagedModel<CozinhaModel> listar(Pageable pageable);

        @ApiOperation("Busca uma cozinha por ID")
        @ApiResponses({
                        @ApiResponse(responseCode = "400", description = "ID da cozinha inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
                        @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
        })
        CozinhaModel buscar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

        @ApiOperation("Cadastra uma cozinha")
        @ApiResponse(responseCode = "201", description = "Cozinha criada", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
        Cozinha adicionar(
                        @ApiParam(name = "corpo", value = "Representação de uma nova cozinha", required = true) CozinhaInput cozinhaInput);

        @ApiOperation("Atualiza uma cozinha por ID")
        @ApiResponse(responseCode = "200", description = "Cozinha atualizada", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
        Cozinha atualizar(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId,
                        @ApiParam(name = "corpo", value = "Representação de uma cozinha com os novos dados") CozinhaInput cozinhaInpu);

        @ApiOperation("Exclui uma cozinha por ID")
        @ApiResponse(responseCode = "204", description = "Cozinha excluída", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Cozinha não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
        void remover(@ApiParam(value = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

}
