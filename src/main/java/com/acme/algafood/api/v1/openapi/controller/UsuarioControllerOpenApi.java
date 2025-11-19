package com.acme.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.acme.algafood.api.exceptionhandler.Problem;
import com.acme.algafood.api.v1.model.request.SenhaInput;
import com.acme.algafood.api.v1.model.request.UsuarioInput;
import com.acme.algafood.api.v1.model.response.UsuarioModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Usuários")
public interface UsuarioControllerOpenApi {
        @Operation(summary = "Lista os usuários")
        CollectionModel<UsuarioModel> listar();

        @Operation(summary = "Busca um usuário por ID")
        @ApiResponses({
                        @ApiResponse(responseCode = "400", description = "ID do usuário inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
                        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        })
        UsuarioModel buscar(
                        @Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId);

        @Operation(summary = "Cadastra um usuário")
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Usuário cadastrado")
        })
        UsuarioModel adicionar(
                        @Parameter(description = "Representação de um novo usuário", required = true) UsuarioInput usuarioInput);

        @Operation(summary = "Atualiza um usuário por ID")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Usuário atualizado"),
                        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        })
        UsuarioModel atualizar(
                        @Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId,

                        @Parameter(description = "Representação de um usuário com os novos dados", required = true) UsuarioInput usuarioInput);

        @Operation(summary = "Atualiza a senha de um usuário")
        @ApiResponses({
                        @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
                        @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
        })
        void alterarSenha(
                        @Parameter(description = "ID do usuário", example = "1", required = true) Long usuarioId,

                        @Parameter(description = "Representação de uma nova senha", required = true) SenhaInput senha);

}
