package com.acme.algafood.api.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.model.request.FotoProdutoInput;
import com.acme.algafood.api.model.response.FotoProdutoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {

        @ApiOperation("Atualiza a foto do produto de um restaurante")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Foto do produto atualizada", content = @Content(schema = @Schema(implementation = Problem.class))),
                        @ApiResponse(responseCode = "404", description = "Produto de restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        })
        FotoProdutoModel atualizarFoto(
                        @ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,

                        @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId,

                        FotoProdutoInput fotoProdutoInput, @ApiParam(hidden = true) MultipartFile arquivo)
                        throws IOException;

        @ApiOperation("Exclui a foto do produto de um restaurante")
        @ApiResponses({
                        @ApiResponse(responseCode = "204", description = "Foto do produto excluída"),
                        @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
                        @ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
        })
        void excluir(
                        @ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,

                        @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);

        @ApiOperation(value = "Busca a foto do produto de um restaurante", produces = "application/json, image/jpeg, image/png")
        @ApiResponses({
                        @ApiResponse(responseCode = "400", description = "ID do restaurante ou produto inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
                        @ApiResponse(responseCode = "404", description = "Foto de produto não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
        })
        FotoProdutoModel buscar(
                        @ApiParam(value = "ID do restaurante", example = "1", required = true) Long restauranteId,

                        @ApiParam(value = "ID do produto", example = "1", required = true) Long produtoId);

        @ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
        ResponseEntity<?> servir(Long restauranteId, Long produtoId, String acceptHeader)
                        throws HttpMediaTypeNotAcceptableException;

}
