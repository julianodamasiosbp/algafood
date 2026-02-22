package com.acme.algafood.api.v1.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.acme.algafood.api.v1.model.response.UsuarioModel;

import lombok.Data;

@Data
public class UsuariosModelOpenApi {

    private UsuariosEmbeddedModelOpenApi _embedded;
    private Links _links;

    @Data
    public class UsuariosEmbeddedModelOpenApi {

        private List<UsuarioModel> usuarios;

    }

}
