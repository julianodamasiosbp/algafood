package com.acme.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.model.request.RestauranteInput;
import com.acme.algafood.api.model.response.RestauranteApenasNomeModel;
import com.acme.algafood.api.model.response.RestauranteBasicoModel;
import com.acme.algafood.api.model.response.RestauranteModel;
import com.acme.algafood.api.openapi.model.RestauranteBasicoModelOpenApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

        @ApiOperation(value = "Lista os restaurantes", response = RestauranteBasicoModelOpenApi.class)
        @ApiImplicitParams({
                        @ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome", name = "projecao", paramType = "query", type = "string")
        })
        CollectionModel<RestauranteBasicoModel> listar();

        @ApiOperation(value = "Lista restaurantes", hidden = true)
        CollectionModel<RestauranteApenasNomeModel> listarApenasNomes();

        @ApiOperation("Busca um restaurante por ID")
        @ApiResponses({
                        @ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
                        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        })
        RestauranteModel buscar(
                        @ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

        @ApiOperation("Cadastra um restaurante")
        @ApiResponse(responseCode = "201", description = "Restaurante criado", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        RestauranteModel adicionar(
                        @ApiParam(name = "corpo", value = "Representação de um novo restaurante") RestauranteInput restauranteInput);

        @ApiOperation("Atualiza um restaurante por ID")
        @ApiResponse(responseCode = "200", description = "Restaurante atualizado", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        RestauranteModel atualizar(
                        @ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId,
                        @ApiParam(name = "corpo", value = "Representação de um novo restaurante") RestauranteInput restauranteInput);

        @ApiOperation("Ativa um restaurante por ID")
        @ApiResponse(responseCode = "204", description = "Restaurante ativado", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        ResponseEntity<Void> ativar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

        @ApiOperation("Desativa um restaurante por ID")
        @ApiResponse(responseCode = "204", description = "Restaurante desativado", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        ResponseEntity<Void> inativar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

        @ApiOperation("Ativa multiplos restaurantes por IDs")
        @ApiResponse(responseCode = "204", description = "Restaurantes ativados", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        void ativarMultiplos(
                        @ApiParam(name = "corpo", value = "IDs de restaurantes", required = true) List<Long> restaurantesIds);

        @ApiOperation("Desativa multiplos restaurantes por IDs")
        @ApiResponse(responseCode = "204", description = "Restaurantes desativados", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        void inativarMultiplos(
                        @ApiParam(name = "corpo", value = "IDs de restaurantes", required = true) List<Long> restaurantesIds);

        @ApiOperation("Fecha um restaurante por ID")
        @ApiResponse(responseCode = "204", description = "Restaurante fechado", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        ResponseEntity<Void> fechar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

        @ApiOperation("Abre um restaurante por ID")
        @ApiResponse(responseCode = "204", description = "Restaurante aberto", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        ResponseEntity<Void> abrir(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

}
