package com.acme.algafood.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.model.request.FormaPagamentoInput;
import com.acme.algafood.api.model.response.FormaPagamentoModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Api(tags = "Formas de Pagamento")
public interface FormaPagamentoControllerOpenApi {

        @ApiOperation(value = "Lista as formas de pagamento")
        ResponseEntity<List<FormaPagamentoModel>> listar(ServletWebRequest request);

        @ApiOperation("Busca uma forma de pagamento por ID")
        @ApiResponses({
                        @ApiResponse(responseCode = "400", description = "ID da forma de pagamento inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
                        @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
        })
        ResponseEntity<FormaPagamentoModel> buscar(
                        @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId,
                        ServletWebRequest request);

        @ApiOperation("Cadastra uma forma de pagamento")
        @ApiResponse(responseCode = "201", description = "Forma de pagamento criada", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
        FormaPagamentoModel adicionar(
                        @ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento", required = true) FormaPagamentoInput formaPagamentoInput);

        @ApiOperation("Atualiza uma forma de pagamento por ID")
        @ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
        FormaPagamentoModel atualizar(
                        @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId,
                        @ApiParam(name = "corpo", value = "Representação de uma forma de pagamento com os novos dados", required = true) FormaPagamentoInput formaPagamentoInput);

        @ApiOperation("Exclui uma forma de pagamento por ID")
        @ApiResponse(responseCode = "204", description = "Forma de pagamento excluída", content = @Content(schema = @Schema(implementation = Problem.class)))
        @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
        public void remover(
                        @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId);

}
