package com.acme.algafood.api.openapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.model.request.EstadoInput;
import com.acme.algafood.api.model.response.EstadoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

    public List<EstadoModel> listar();

    @ApiOperation("Busca um estado por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID do estado inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    public EstadoModel buscar(@ApiParam(value = "ID de um estado", example = "1", required = true) Long id);

    @ApiOperation("Cadastra um estado")
    @ApiResponse(responseCode = "201", description = "Estado criado", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public EstadoModel adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo estado") EstadoInput estadoInput);

    @ApiOperation("Atualiza um estado por ID")
    @ApiResponse(responseCode = "200", description = "Estado atualizado", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public EstadoModel atualizar(@ApiParam(value = "ID de um estado", example = "1", required = true) Long estadoId,
            @ApiParam(name = "corpo", value = "Representação de um novo estado") EstadoInput estadoInput);

    @ApiOperation("Exclui um estado por ID")
    @ApiResponse(responseCode = "204", description = "Estado excluído", content = @Content(schema = @Schema(implementation = Problem.class)))
    @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    public void remover(@ApiParam(value = "ID de um estado", example = "1", required = true) Long id);

}
